;; solution - combine func
(defn create
    [alph base] 
	(reduce 
	(fn 
		[res y] 
        (concat (map (fn [x] (concat x [y])) 
        (filter (fn [x] (not (= (last x) y))) base)) res)) 
    (cons (list) alph)))
(defn prepfirst
    [alph] (reduce (fn [x y] (cons (list y) x)) (cons (list) alph)))
(defn combine    
    [alph, n] (reduce (fn [x y] (create alph x)) (cons (prepfirst alph) (range 1 n))))