; ctrl+alt+c ctrl+alt+j
; ctrl+shift+p
(ns test.core
  (:gen-class))

(defn cls
  [n]
  (do
    (if (> n 0)
      (do
        (println "")
        (cls (dec n)))
      0)))

(defn my-map
  [f arr]
  (let [arr-f (first arr)]
    (if arr-f
      (do
        (f arr-f)
        (my-map f (rest arr))))))

(defn my-sqr
  [x]
  (* x x))

(defn -main [& arg1]
  (do
    (cls 10)

    (println "let a")
    (let [a '(10 20 30)]
      (println a)

      (println "map")
      (map my-sqr a)

      (println "my-map")
      (my-map my-sqr a)
    )
  )
)


(-main `(10 20 50))


(defn inc-coll-1 [coll]
  (if (> (count coll) 0)
    (cons (inc (first coll))
          (inc-coll-1 (rest coll)))
    (list)))
