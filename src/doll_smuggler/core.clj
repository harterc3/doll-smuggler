(ns doll-smuggler.core
	(:require [clojure-csv.core :refer [parse-csv]]
		[clojure.string :as string]
		[clojure.tools.cli :refer [parse-opts]])
	(:use clojure.java.io)
	(:gen-class)
)

(defn exit [status msg]
	(println msg)
	(System/exit status))

(def cli-options
  [["-f" "--file FILE" "Path to input file"
    :default "./input/dolls1.csv"]
   ["-w" "--weight WEIGHT" "Maximum weight allowed"
    :default 400
    :parse-fn #(Integer/parseInt %)
    :validate [#(<= 0 %) "Must be a number greater than or equal to 0"]]
   ["-h" "--help"]])

(defn error-msg [errors]
	(str "The following errors occurred while parsing your command:\n\n"
		(string/join \newline errors)
	)
)

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s ))
)

(defn parse-row [row]
  (let [v (first (parse-csv row))]
    (zipmap [:name :weight :value] [(get v 0) (parse-int (get v 1)) (parse-int(get v 2))])
    )
  )

(defn read-csv [fname]
	(with-open [file (reader fname)]
		(doall (for [line (line-seq file)]
			(parse-row line)
		))
	)
)

; main algorithm for determining which dolls to pack
(defn smuggle_calculate [ind weight dolls]
	(cond
		(< ind 0) [[] 0]
		(= weight 0) [[] 0]
		:else
		(let [doll_ind (nth dolls ind) 
			weight_ind (:weight doll_ind)
			value_ind (:value doll_ind)]
			(if (> weight_ind weight)
				(smuggle_calculate (dec ind) weight dolls)
				(let [[inds_no val_no :as no] (smuggle_calculate (dec ind) weight dolls)
					[inds_yes val_yes :as yes] (smuggle_calculate (dec ind) (- weight weight_ind) dolls)]
					(if (> (+ val_yes value_ind) val_no)
						[(conj inds_yes ind) (+ val_yes value_ind)]
						no
					)
				)
			)
		)
	)
)

(defn -main [& args]
	(let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
		(cond
			(:help options) (exit 0 summary)
			errors (exit 1 (error-msg errors))
		)
		(let [dolls (read-csv (:file options))
			[doll_inds total_value] (smuggle_calculate (- (count dolls) 1) (:weight options) dolls)
			valuable_dolls (map (partial nth dolls) doll_inds)]
			(println "Doll names:")
			(doseq [name (map :name valuable_dolls)]
  				(println name)
			)
			(println "Total value:" (reduce + (map :value valuable_dolls)))
			(println "Total weight:" (reduce + (map :weight valuable_dolls)))
		)	
	)
)





