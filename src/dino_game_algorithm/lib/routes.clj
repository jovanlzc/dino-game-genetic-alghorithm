(ns dino-game-algorithm.lib.routes
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [clojure.pprint :as pp]
   [clojure.string :as str]
   [clojure.data.json :as json]
   [dino-game-algorithm.lib.app :as app])
  (:gen-class))


(defn cross-over-route
  "Cross over hromozoma"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (app/cross-over (req :params)))})

(defn mutate-route
  "Mutate hromozoma"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (app/mutate (req :params)))})

(defn predict-route
  "Predict skoka"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (app/predict (req :params)))})
