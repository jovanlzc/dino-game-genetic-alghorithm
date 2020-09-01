(ns dino-game-algorithm.core
  (:use compojure.core
        ring.middleware.json
        ring.util.response)
  (:require [compojure.route :as route]
            [dino-game-algorithm.view :as view]))

(defn foo
  [x]
  (str  "Hello " x))

(defn select [chromosomes](take 2 chromosomes))

(defn swap-elements [offset1 offset2] 
  ())

(defn change-chromosome [chromosomes new-offset]
  ())

(defn cross-over [chromosomes]
  (let [[offset1 offset2 &rest] chromosomes]
    (let [cross-over-point (int (Math/floor (* (rand) (count offset1))))]
     (loop [remaining-offsets1 (take cross-over-point offset1) remaining-offsets2 (take cross-over-point offset2) new-offset1 [] new-offset2 []]
       (if (or (empty? remaining-offsets1) (empty? remaining-offsets2))
         (change-chromosome chromosomes new-offset1 new-offset2)
         (if (< (count new-offset1) cross-over-point)
           (let [swapped-elements (swap-elements (take 1 remaining-offsets1)  (take 1 remaining-offsets2))] 
             (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first swapped-elements)) (conj new-offset2 (second swapped-elements))) )
           (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (take 1 remaining-offsets1) (conj new-offset2 (take 2 remaining-offsets2))))
           ))))))

(defroutes my_routes
  (GET "/" [] (view/index-page))
  (GET "/rest" [] (response {:email "jovan.lazic.10@gmail.com"}))
  (route/resources "/"))

(def app (wrap-json-response my_routes))