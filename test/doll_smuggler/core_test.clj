(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def dolls1
	[
		{ :name "luke"		:weight 9	:value 150 } 
		{ :name "anthony"	:weight 13	:value 35 }  
		{ :name "candice"	:weight 153	:value 200 } 
		{ :name "dorothy"	:weight 50	:value 160 } 
		{ :name "puppy"		:weight 15	:value 60 } 
		{ :name "thomas"	:weight 68	:value 45 }  
		{ :name "randal"	:weight 27	:value 60 } 
		{ :name "april"		:weight 39	:value 40 } 
		{ :name "nancy"		:weight 23	:value 30 } 
		{ :name "bonnie"	:weight 52	:value 10 } 
		{ :name "marc"		:weight 11	:value 70 } 
		{ :name "kate"		:weight 32	:value 30 } 
		{ :name "tbone"		:weight 24	:value 15 } 
		{ :name "tommy"		:weight 48	:value 10 }  
		{ :name "uma"		:weight 73	:value 40 } 
		{ :name "grumpkin"	:weight 42	:value 70 } 
		{ :name "dusty"		:weight 43	:value 75 } 
		{ :name "grumpy"	:weight 22	:value 80 }  
		{ :name "eddie"		:weight 7	:value 20 } 
		{ :name "tory"		:weight 18	:value 12 } 
		{ :name "sally"		:weight 4	:value 50 } 
		{ :name "babe"		:weight 30	:value 10 }
	]
)

(def dolls2
	[
		{ :name "cory"		:weight 10000	:value 1 }
	]
)

(def dolls3
	[
		{ :name "luke"		:weight 1	:value 150 } 
		{ :name "anthony"	:weight 2	:value 35 }  
		{ :name "candice"	:weight 2	:value 200 } 
		{ :name "dorothy"	:weight 1	:value 160 } 
	]
)

(def dolls4
	[
	]
)

(deftest smuggle_calculate_for_dolls1
	(testing
		"Test algorithm against 'dolls1' data"
		(let [[inds] (smuggle_calculate (-> dolls1 count dec) 400 dolls1)]
			(is (= (set inds) #{0 1 2 3 4 6 10 15 16 17 18 20}))
			(is (= (reduce + (map (comp :weight dolls1) inds)) 396))
		)
	)
)

(deftest smuggle_calculate_for_dolls1_with_higher_maximum_weight
	(testing
		"Test algorithm against 'dolls1' data"
		(let [[inds] (smuggle_calculate (-> dolls1 count dec) 600 dolls1)]
			(is (= (set inds) #{0 1 2 3 4 5 6 7 8 10 11 12 15 16 17 18 19 20}))
			(is (= (reduce + (map (comp :weight dolls1) inds)) 600))
		)
	)
)

(deftest smuggle_calculate_for_dolls2
	(testing
		"Test algorithm against 'dolls2' data"
		(let [[inds] (smuggle_calculate (-> dolls2 count dec) 200 dolls2)]
			(is (= (set inds) #{}))
			(is (= (reduce + (map (comp :weight dolls2) inds)) 0))
		)
	)
)

(deftest parse_csv_for_dolls1
	(testing
		"Testing csv parsing functions for 'dolls1'"
		(let [csv_result (read-csv "./input/dolls1.csv")]
			(is (= csv_result dolls1))
		)
	)
)

(deftest parse_csv_for_dolls2
	(testing
		"Testing csv parsing functions for 'dolls2'"
		(let [csv_result (read-csv "./input/dolls2.csv")]
			(is (= csv_result dolls2))
		)
	)
)

(deftest smuggle_calculate_for_dolls3
	(testing
		"Test algorithm against 'dolls3' data"
		(let [[inds] (smuggle_calculate (-> dolls3 count dec) 10 dolls3)]
			(is (= (set inds) #{ 0 1 2 3}))
			(is (= (reduce + (map (comp :weight dolls3) inds)) 6))
		)
	)
)

(deftest smuggle_calculate_for_dolls4
	(testing
		"Test algorithm against 'dolls4' data"
		(let [[inds] (smuggle_calculate (-> dolls4 count dec) 0 dolls4)]
			(is (= (set inds) #{}))
			(is (= (reduce + (map (comp :weight dolls4) inds)) 0))
		)
	)
)

(deftest parse_csv_for_dolls3
	(testing
		"Testing csv parsing functions for 'dolls3'"
		(let [csv_result (read-csv "./input/dolls3.csv")]
			(is (= csv_result dolls3))
		)
	)
)

(deftest parse_csv_for_dolls4
	(testing
		"Testing csv parsing functions for 'dolls4'"
		(let [csv_result (read-csv "./input/dolls4.csv")]
			(is (= csv_result dolls4))
		)
	)
)

(deftest read_csv_for_fake_file
	(testing
		"Open file that doesn't exist should not crash program"
		(let [csv_result (read-csv "blahh.csv")]
			(is (= csv_result nil))
		)
	)
)
