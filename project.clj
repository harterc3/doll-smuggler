(defproject doll-smuggler "0.1.0-SNAPSHOT"
	:description "Function for optimal drug trafficking."
	:url "https://github.com/harterc3/doll-smuggler"
	:license {:name "Eclipse Public License"
    	:url "http://www.eclipse.org/legal/epl-v10.html"}
	:dependencies [[org.clojure/clojure "1.7.0"]
		[org.clojure/tools.cli "0.3.3"]
		[clojure-csv/clojure-csv "2.0.1"]]
	:main ^:skip-aot doll-smuggler.core
	:target-path "target/%s"
	:profiles {:uberjar {:aot :all}})
