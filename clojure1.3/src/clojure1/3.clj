  (ns clojure1.3)

  (defn my-map [fnc list]
    (reduce (fn [last current] (conj last (fnc current))) [] list))

  (defn my-filter [fnc, list]
    (reduce (fn [last current] (if (fnc current) (conj last current) last)) [] list))

  (defn -main
    "Определить функции my-map и my-filter, аналогичные map (для одного списка)
    и filter, вырази их через reduce и базовые операции над списками"
    [& args]
    (println (my-map (fn [x] (* x x)) '(1 2 3 4 5)))
    (println (my-filter (fn [x] (= 0 (mod x 3))) '(1 2 3 4 5))))