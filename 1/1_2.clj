;; solution - combine func
(defn foreach 
	([base sym] (foreach base sym 0 (list)))
	([base sym index res]
		(if (< index (- (count base) 1))
            (if (.endsWith (nth base index) sym)
                (recur base sym (inc index) res)
                (recur base sym (inc index) (cons (.concat (nth base index) sym) res)))
            (if (.endsWith (nth base index) sym)
                res
                (cons (.concat (nth base index) sym) res)))))
(defn create 
	([alph base] (create alph base 0 (list)))
	([alph base index res]
    (if (< index (count alph))
        (recur alph base (inc index) (concat (foreach base (nth alph index)) res))
        res)))	
(defn combine 
	([alph, n] (combine alph (dec n) alph))
	([alph, n, res] 
	    (if (> n 0) 
            (recur alph (dec n) (create alph res))
            res
    )))