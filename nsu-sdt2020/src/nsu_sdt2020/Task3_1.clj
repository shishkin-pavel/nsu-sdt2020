(ns Task3_1)

(defn block-filter [coll pred block-size res]
  (if (empty? coll) res
                    (recur (drop block-size coll) pred block-size (conj res (future (filter pred (take block-size coll)))))))

(defn parallel-filter [coll pred block-size]
  (->> (block-filter coll pred block-size [])
       (doall)
       (map deref)
       (flatten)))

;(defn simplify-structure [result]
;  (loop [res result, acc '()]
;    (if (= (count res) 0) acc (recur (rest res) (concat acc (first res))))))

(defn check-test [n description expected-coll actual-coll]
  (print "< Тест № " n " >  ")
  (print "ExpectedAmount: " (count expected-coll))
  (print ". ActualAmount: " (count actual-coll))
  (print ". => " (= (count expected-coll) (count actual-coll)))
  (print ".       Expected: " expected-coll)
  (print ". Actual: " expected-coll)
  (print ". => " (= expected-coll actual-coll))
  (println ".  -  " description)
  )

(defn -main
  [& args]
  (println "----Обычный фильтр----")
  (time (filter (fn [n] (= 0 (mod n 3))) (range 1 700)))
  (println "----Параллельный фильтр----")
  (time (parallel-filter (range 1 700) (fn [n] (= 0 (mod n 3))) 100))
  ;(println (time(parallel-filter (range 1 700) (fn [n] (= 0 (mod n 3))) 10))) ; => Разная производительность при разном размере блока

  ;tests
  (println "\n----Тесты----")
  (check-test 1 "Пустая коллекция" '() (parallel-filter '() (fn [n] (= 0 (mod n 3))) 3))
  (check-test 2 "Пустой" '() (parallel-filter (range 1 3) (fn [n] (= 0 (mod n 3))) 3))
  (check-test 3 "Размер равен блоку, 1 элемент" '(3) (parallel-filter (range 1 4) (fn [n] (= 0 (mod n 3))) 3))
  (check-test 4 "Размер равен двум блокам, 2 элемента" '(3 6) (parallel-filter (range 1 7) (fn [n] (= 0 (mod n 3))) 3))
  (check-test 5 "Размер равен блоку, 2 элемента в нем" '(3 6) (parallel-filter (range 1 7) (fn [n] (= 0 (mod n 3))) 6))
  (check-test 6 "Блок чуть меньше размера (всего 2 блока, второй неполный), 2 элемента" '(3 6) (parallel-filter (range 1 7) (fn [n] (= 0 (mod n 3))) 5))
  (check-test 7 "Размер меньше блока, 2 элемента" '(3 6) (parallel-filter (range 1 7) (fn [n] (= 0 (mod n 3))) 10))

  )
