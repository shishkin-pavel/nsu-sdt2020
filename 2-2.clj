(ns lab1.core
  (:gen-class))

(defn calc-trap [f a b]
    ;(println "calc-trap" a b)
  (* (/ (+ (f a) (f b)) 2) (- b a)))

(defn get-integrator [f begin step]
    (let [ls (iterate #(+ % (calc-trap f % (+ % step)))
                      begin)]
        (fn [k]
            (nth ls (/ k step))
            ;(take (/ k step) ls)
        )
    )
)

(let [integrator (get-integrator (fn [x] 1) 0 1)]
    (time (integrator 10000))
    (time (integrator 10015))
    (time (integrator  9985))
)
