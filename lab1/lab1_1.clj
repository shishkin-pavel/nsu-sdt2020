(ns clojuretest.lab1_1)

(defn single_connector [word lst]
  (if (empty? lst)
    `()
    (let [next-words (single_connector word (rest lst))]
    (if (= (first (first word)) (first lst))
      next-words
      (cons (cons (first lst) (first word)) next-words)))))

(defn connector
  ([word lst]
   (if (empty? word)
     []
     (concat (single_connector word lst) (connector (rest word) lst)))))

(defn counter [word lst n]
  (cond
    (> (dec n) 0) (counter (connector word lst) lst (dec n))
    (= (dec n) 0) (connector word lst)))

(defn list_of_lists [lst]
  (if (empty? lst)
    `()
    (cons (list (first lst)) (list_of_lists (rest lst)))))

(defn run [lst n]
  (counter '(()) lst n))

(println (run `(:a :b :c) 3))
(println (run ["a" "b" "c"] 2))
(println (run ["a" "b" "c"] 3))
(println "!!!")
(println (run `("a" (:b 1) [:c :d]) 2))
(println (run `("a" (:b 1) ["c" "d"]) 3))