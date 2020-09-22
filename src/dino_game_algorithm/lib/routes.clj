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
   :body (str (json/write-str (app/cross-over (get-in req [:params :chromo]))))})

(defn mutate-route
  "Mutate hromozoma"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
    :body (str (json/write-str (app/mutate (get-in req [:params :chromo]))))
   })

(defn predict-route
  "Predict skoka"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (-> (str (json/write-str(app/predict (get-in req [:params :weights]) (get-in req [:params :inputs])))))
   })

(defn initiliaze-route
  "Pocetne vrednosti tezinskih koeficijenata"
  [req]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (str (json/write-str (app/initiliaze)))})
