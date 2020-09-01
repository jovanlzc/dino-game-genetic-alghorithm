(ns dino-game-algorithm.lib.routes
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [clojure.pprint :as pp]
   [clojure.string :as str]
   [clojure.data.json :as json]
   [dino-game-algorithm.lib.api :as api])
  (:gen-class))


(defn cross-over-route
  "Cross over hromozoma"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (api/cross-over (req :params)))})

