(ns lab1.core
  (:gen-class))

(defn calc-trap [f a b]
  (* (/ (+ (f a) (f b)) 2) (- b a)))

;;--------------
;; better way
(let [seqq (map #(* 2 %) (range))]
    (->>
        (map
            #(calc-trap (fn [x] 1) %1 %2)
            seqq
            (rest seqq)
        )
        (reductions + 0)
        (take 10)
    )
)

;;--------------

(defn get-integrator [f begin step]
    (let [ls (iterate #(+ % (calc-trap f % (+ % step)))
                      begin)]
        (fn [k]
            (nth ls (/ k step))
        )
    )
)

(let [integrator (get-integrator (fn [x] 1) 0 1)]
    (time (integrator 10000))
    (time (integrator 10015))
    (time (integrator  9985))
)

