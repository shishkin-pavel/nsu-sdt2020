(ns lab2.core
  (:use lab2.task1)
  (:use lab2.task2))

(defn -main [& args]
  (println (task1 (fn [x] x) 2.5 10.0)))
