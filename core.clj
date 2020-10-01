(ns lab1.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn my-map [f data]
  (reduce #(conj %1 (f %2))
          []
          data))

(defn my-filter [f data]
  (reduce #(
            (do
              (println "%1" %1)
              (println "%2" %2)
              (if true ;(f %2)
                  (conj %1 %2)
                  (%1))))
          []
          data))

;;(println (my-map #(* %1 %1) '(2 4 3)))
;;(println (my-map even? '(2 4 3)))
(println "my-filter")
(println (my-filter even? (range 1 10)))


