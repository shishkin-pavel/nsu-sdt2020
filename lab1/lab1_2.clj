(ns clojuretest.lab1_2)

(defn single_connector [word lst]
  (loop [acc `() word word lst lst]
    (if (empty? lst)
      acc
      (if (= (first word) (first lst))
        (recur acc word (rest lst))
        (recur (cons (cons (first lst) word) acc)
               word (rest lst))))))

(defn connector [words lst]
  (loop [acc [] words words]
    (if (empty? words)
      acc
      (recur (concat acc (single_connector (first words) lst)) (rest words))
      ))
  )

(defn counter1 [word lst n]
  (cond
    (= (dec n) 1) (connector word lst)
    (> (dec n) 1) (recur (connector word lst) lst (dec n)))
  )


(defn counter2 [word lst n]
  (nth (iterate #(connector % lst) word) n))

(defn list_of_lists [lst]
  (if (empty? lst)
    `()
    (cons (list (first lst)) (list_of_lists (rest lst)))))

(defn run [lst n]
  (counter2 `(()) lst n))

(println (run ["a" "b" "c"] 2))
(println (run ["a" "b" "c"] 3))
(println "!!!")
(println (run `("a" (:b 1) ["c" "d"]) 2))
(println (run `("a" (:b 1) ["c" "d"]) 3))