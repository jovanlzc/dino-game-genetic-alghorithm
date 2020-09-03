(defproject dino-game-algorithm "0.1.0-SNAPSHOT"
  :description "Genetic Algorithm playing T-Rex (Dino) game"
  :url "www.google.com"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.1"]
                 ; Compojure - A basic routing library
                 [compojure "1.6.1"]
                 ; Our Http library for client/server
                 [http-kit "2.3.0"]
                 ; Ring defaults - for query params etc
              
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [ring-cors "0.1.13"]
                 ; Clojure data.JSON library
                 [org.clojure/data.json "0.2.6"]]

  ;:main ^:skip-aot dots-and-boxes.core
  :main dino-game-algorithm.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})