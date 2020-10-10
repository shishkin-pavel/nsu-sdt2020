(ns clojuretest.lab1_4)

(defn single_connector [word lst]
  (map #(cons % word) (filter #(not= (first word) %) lst)))


(defn connector [word lst]
  (reduce concat (map #(single_connector % lst) word)))

(defn run
  ([lst n] (run lst (map list lst) n))
  ([lst word n] (if (> (dec n) 0)
                      (run lst (reduce concat (map #(single_connector % lst) word)) (dec n))
                      word)))

(defn run2 [lst n]
  (let [word (map list lst)]
    (nth (iterate #(connector % lst) word) (dec n)))
  )

(println (run2 `("a" "b" "c") 3))
;(println (run `("a" (:b 1) ["c" "d"]) 3))