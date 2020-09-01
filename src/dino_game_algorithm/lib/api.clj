(ns dino-game-algorithm.lib.api
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [clojure.pprint :as pp]
   [clojure.string :as str]
   [clojure.data.json :as cjson])
  (:gen-class))




(defn select [chromosomes] (take 2 chromosomes))


(defn change-chromosome [chromosomes new-offset1 new-offset2]
  (conj (drop-last 2 chromosomes) new-offset1 new-offset2))

(defn cross-over [{chromosomes :chromosomes :as vector}]
  (let [[offset1 offset2 &rest] chromosomes]
    (let [cross-over-point (int (Math/floor (* (rand) (count offset1))))]
      (loop [remaining-offsets1 offset1 remaining-offsets2 offset2 new-offset1 [] new-offset2 []]
        (if (or (empty? remaining-offsets1) (empty? remaining-offsets2))
          (change-chromosome chromosomes new-offset1 new-offset2)
          (if (< (count new-offset1) cross-over-point)
            (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets2)) (conj new-offset2 (first remaining-offsets1)))
            (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets1)) (conj new-offset2 (first remaining-offsets2)))))))))