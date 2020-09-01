(ns dino-game-algorithm.core
 
  (:require [org.httpkit.server :as server]
            [compojure.core :as cc]
            [compojure.route :as route]
            [ring.middleware.defaults :as rmd]
            [ring.middleware.json :as rmjs]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [dino-game-algorithm.lib.routes :as routes])
  (:gen-class))

(defn select [chromosomes](take 2 chromosomes))


(defn change-chromosome [chromosomes new-offset1 new-offset2]
  (conj (drop-last 2 chromosomes) new-offset1 new-offset2))

(defn cross-over [chromosomes]
  (let [[offset1 offset2 &rest] chromosomes]
    (let [cross-over-point (int (Math/floor (* (rand) (count offset1))))]
     (loop [remaining-offsets1 offset1 remaining-offsets2 offset2 new-offset1 [] new-offset2 []]
       (if (or (empty? remaining-offsets1) (empty? remaining-offsets2))
         (change-chromosome chromosomes new-offset1 new-offset2)
         (if (< (count new-offset1) cross-over-point)
           (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets2)) (conj new-offset2 (first remaining-offsets1)))
           (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets1)) (conj new-offset2 (first remaining-offsets2)))
           )))
         
         )
  ))

(cc/defroutes app-routes
  
  (cc/POST "/cross-over" [] routes/cross-over-route))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port 3000]
    (server/run-server (rmjs/wrap-json-params (rmjs/wrap-json-response (rmd/wrap-defaults #'app-routes (assoc-in rmd/site-defaults [:security :anti-forgery] false)))) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))