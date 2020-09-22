(ns dino-game-algorithm.lib.app
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [clojure.pprint :as pp]
   [clojure.string :as str]
   [clojure.data.json :as cjson])
  (:gen-class))

(defn mutate-gene [chromosome] 
  (println (str "mutatate-gene:" chromosome))
  (let [mutation-point (int (Math/floor (rand (count chromosome))))]
    (println (str "mutation-point" mutation-point))
    (loop [remaining-genes chromosome new-chromosome []] 
    (if (empty? remaining-genes) 
    (do (println (str "after-mutation:" new-chromosome))new-chromosome)
      (if (=  (count new-chromosome) mutation-point)
           (recur (drop 1 remaining-genes) (conj new-chromosome (* (- (rand) 0.69) 1.44)))
        (recur (drop 1 remaining-genes) (conj new-chromosome (first remaining-genes))))
      ))))

(defn mutate [chromosomes]
  (println (str "pristigli hromozom" chromosomes))
  (loop [remaining-chromosomes chromosomes mutation-chromosomes []]
    (if (empty? remaining-chromosomes)
      mutation-chromosomes
      (recur (drop 1 remaining-chromosomes) (conj mutation-chromosomes (mutate-gene (first remaining-chromosomes)))))))

(defn select [chromosomes] (take 2 chromosomes))
(defn change-chromosome [chromosomes new-offset1 new-offset2]
  (conj (drop-last 2 chromosomes) new-offset1 new-offset2))

(defn cross-over [chromosomes]
  (let [[offset1 offset2 &rest] chromosomes]
    (let [cross-over-point (inc (rand-int 2))]
      (println (str "cross-over-point:" cross-over-point))
      (println (str "offset1:" offset1))
      (println (str "offset2:" offset2))
      (loop [remaining-offsets1 offset1 remaining-offsets2 offset2 new-offset1 [] new-offset2 []]
        (if (or (empty? remaining-offsets1) (empty? remaining-offsets2))
        (do (println (str "newoffset1" new-offset1))
            (println (str "newoffset2" new-offset2))
            (change-chromosome chromosomes new-offset1 new-offset2))
          (if (< (count new-offset1) cross-over-point)
            (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets2)) (conj new-offset2 (first remaining-offsets1)))
            (recur (drop 1 remaining-offsets1) (drop 1 remaining-offsets2) (conj new-offset1 (first remaining-offsets1)) (conj new-offset2 (first remaining-offsets2)))))))))

(defn initiliaze [] 
  (loop [weights-array []]
    (if (= (count weights-array) 4)
     weights-array
    (recur (conj weights-array (* (- (rand) 0.5) 2))))))

(defn predict [weights inputs]
  (loop [remaining-weights weights remaining-inputs inputs value 0]
    (if (empty? remaining-weights)
      (if (< value 0)
        0
        1)
        (if (empty? remaining-inputs)
          (recur (drop 1 remaining-weights) [] (+ (first remaining-weights) value))
          (recur (drop 1 remaining-weights) (drop 1 remaining-inputs) (+ (* (first remaining-weights) (first remaining-inputs)) value))))))