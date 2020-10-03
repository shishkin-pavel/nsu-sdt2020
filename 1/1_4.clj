;; solution - combine func
(defn create
    [alph base] (reduce (fn [res y] (concat (map (fn [x] (if (= (class x) java.lang.String) (concat [x] [y])(concat x [y]))) (filter (fn [x] (if (= (class x) java.lang.String) (not (= x y)) (not (= (last x) y)))) base)) res)) (cons (list) alph)))
(defn combine
    [alph, n] (reduce (fn [x y] (create alph x)) (cons alph (range 1 n))))