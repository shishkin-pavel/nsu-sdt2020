(ns lab1.core
  (:gen-class))

(defn calc-trap [f a b]
    ;(println "calc-trap" a b)
  (* (/ (+ (f a) (f b)) 2) (- b a)))


(let [maxx 10 delta 2]

    (take (/ maxx delta)
        (iterate #(+ % delta) 0)
    )
)

(let [step 5 begin 10]
(let [ls (iterate #(+ % step) begin)]
    (take 10 ls)
)
)


(defn get-integrator [f begin step]
    (let [ls (iterate #(+ % (calc-trap f % (+ % step)))
                      begin)]
        (fn [k]
            ;(nth ls (/ k step))
            (take (/ k step) ls)
        )
    )
)

(let [integrator (get-integrator #(* % 1) 0 1)]
    (time (integrator 10))
    (time (integrator 10))
)


;;(let [integrator (get-integrator #(* % 1) 0 5)]
;;    (time (integrator 10000))
;;    (time (integrator 10015))
;;    (time (integrator 09985))
;;)
