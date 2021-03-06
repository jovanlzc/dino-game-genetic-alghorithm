(ns dino-game-algorithm.core
 
  (:require [org.httpkit.server :as server]
            [compojure.core :as cc]
            [compojure.route :as route]
            [ring.middleware.defaults :as rmd]
            [ring.middleware.json :as rmjs]
            [ring.middleware.cors :refer [wrap-cors]]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [dino-game-algorithm.lib.routes :as routes])
  (:gen-class))

(defn select [chromosomes](take 2 chromosomes))

(cc/defroutes app-routes

  (cc/POST "/cross-over" [] routes/cross-over-route)
  (cc/POST "/mutate" [] routes/mutate-route)
  (cc/POST "/predict" [] routes/predict-route)
  (cc/POST "/initiliaze" [] routes/initiliaze-route))


(def app
  (-> app-routes
      (wrap-cors :access-control-allow-origin [#".*"] :access-control-allow-methods [:get :post :put :delete])))
(defn -main
  "This is our main entry point"
  [& args]
  (let [port 3000]
    (server/run-server (rmjs/wrap-json-params (rmjs/wrap-json-response (rmd/wrap-defaults #'app (assoc-in rmd/site-defaults [:security :anti-forgery] false)))) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))