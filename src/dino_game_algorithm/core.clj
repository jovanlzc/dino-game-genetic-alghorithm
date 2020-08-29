(ns dino-game-algorithm.core
  (:use compojure.core
        ring.middleware.json
        ring.util.response)
  (:require [compojure.route :as route]
            [dino-game-algorithm.view :as view]))

(defn foo
  [x]
  (str  "Hello " x))

(defroutes my_routes
  (GET "/" [] (view/index-page))
  (GET "/rest" [] (response {:email "jovan.lazic.10@gmail.com"}))
  (route/resources "/"))

(def app (wrap-json-response my_routes))