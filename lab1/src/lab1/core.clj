(ns lab1.core
  (:use lab1.task1)
  (:use lab1.task2))

(defn -main [& args]
  (println (task2 `("a" "b" "c") 2)))
