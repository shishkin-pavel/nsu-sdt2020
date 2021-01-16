(ns nsu-sdt2020.Task4)

;порождение переменной
(defn variable [name]
  {:pre [(keyword? name)]}
  (list ::var name))
;проверка типа для переменной
(defn variable? [expr]
  (= (first expr) ::var))

;порождение константы
(defn constant [value]
  (list ::const value))
;проверка типа для константы
(defn constant? [expr]
  (= (first expr) ::const))
;получение значения для переменной
(defn constant-value [expr]
  (second expr))

;операции

;порождение конъюнкции
(defn conjunction [expr & rest]
  (cons ::and (cons expr rest)))
;проверка типа для конъюнкции
(defn conjunction? [expr]
  (= (first expr) ::and))

;порождение дизъюнкции
(defn disjunction [expr & rest]
  (cons ::or (cons expr rest)))
;проверка типа для дизъюнкции
(defn disjunction? [expr]
  (= (first expr) ::or))

;порождение отрицания
(defn negation [expr]
  (list ::not expr))
;проверка типа для отрицания
(defn negation? [expr]
  (= (first expr) ::not))

;порождение импликации
(defn implication [expr1 expr2]
  (list ::impl expr1 expr2))
;проверка типа для импликации
(defn implication? [expr]
  (= (first expr) ::impl))

;получение аргументов
(defn args [expr]
  (rest expr))

;список правил вывода
(def dnf-rules
  (list
    ; Преобразование импликации:  (a -> b) => (!a || b)
    [implication?
     (fn [expr]
       (disjunction (negation (first (args expr))) (second (args expr))))]

    ; Закон Де Моргана для конъюгкции:  !(a && b && ...) => (!a || !b || ...)
    [(fn [expr] (and (negation? expr) (conjunction? (first (args expr)))))
     (fn [expr]
       (apply disjunction
              (map negation (args (first (args expr))))))]

    ; Закон Де Моргана для дизъюнкции:  !(a || b || ...) => (!a && !b && ...)
    [(fn [expr]
       (and (negation? expr) (disjunction? (first (args expr)))))
     (fn [expr]
       (apply conjunction
              (map negation (args (first (args expr))))))]

    ; Убрать двойное отрицание:  (!!a) => (a)
    [(fn [expr]
       (and (negation? expr) (negation? (first (args expr)))))
     (fn [expr]
       (first (args (first (args expr)))))]

    ; Конъюнкция или дизъюнкция с 1 аргументом
    [(fn [expr]
       (and (or (disjunction? expr) (conjunction? expr)) (= (count (args expr)) 1)))
     (fn [expr]
       (first (args expr)))]

    ; Конъюнкция с 0: (...&& 0 && ...) => 0
    [(fn [expr] (and (conjunction? expr) (some constant? (args expr))))
     (fn [expr]
       (let [const-arg (filter constant? (args expr))
             false-const (some (fn [arg] (if (= (constant-value arg) false) arg false)) const-arg)]
         (if (empty? false-const) (cons ::and (args expr)) (constant false))))]

    ; Дизъюнкция с 1: (...|| 1 || ...) => 1
    [(fn [expr] (and (disjunction? expr) (some constant? (args expr))))
     (fn [expr]
       (let [const-arg (filter constant? (args expr))
             true-const (some (fn [arg] (if (= (constant-value arg) true) arg false)) const-arg)]
         (if (empty? true-const) (cons ::or (args expr)) (constant true))))]

    ; Распределительный закон:  (a && b ... && (c || d || ...) && ...) => ((a && b && ... && c ) || (a & b & ... & d) || ...)
    [(fn [expr]
       (and (conjunction? expr) (some disjunction? (args expr))))
     (fn [expr]
       (let [disj-arg (some (fn [arg] (if (disjunction? arg) arg false)) (args expr))
             rest-args (remove (fn [expr] (= expr disj-arg)) (args expr))]
         (apply disjunction (map (fn [arg] (apply conjunction (cons arg rest-args))) (args disj-arg)))))]

    ; Правила идемпотентности:   (a && a && ...) => (a && ...) ,    (a || a || ...) => (a || ...)
    [(fn [expr]
       (and (or (disjunction? expr) (conjunction? expr)) (not (apply distinct? (args expr)))))
     (fn [expr]
       (if (disjunction? expr)
         (cons ::or (distinct (args expr)))
         (cons ::and (distinct (args expr)))
         )
       )]

    ;Отрицание константы: !1 = 0, !0 = 1
    [(fn [expr] (and (negation? expr) (constant? (first (args expr)))))
     (fn [expr] (if (= false (constant-value (first (args expr))))
                  (constant true)
                  (constant false)))]
    ))

(defn find-rule [rules expr]
  (some
    (fn [rule] (if ((first rule) expr) (second rule) false))
    rules))

(defn apply-dnf-rules [expr]
  (if (or (constant? expr) (variable? expr))
    expr                                                                  ;term
    (let [expr2  (cons (first expr) (map apply-dnf-rules (args expr)))    ;apply-matching-rule
          rule  (find-rule dnf-rules expr2)]
      (if rule (rule expr2) expr2))))


(defn to-dnf-process [expr previous]
  (if (= expr previous)
    expr
    (to-dnf-process (apply-dnf-rules expr) expr))
  )

(defn to-dnf  [expr]
  (to-dnf-process (apply-dnf-rules expr) expr))



(defn get-variable-value [values name]
  (if (contains? values name)
    (get values name)
    (throw (Exception. (str "Unknown variable: " name)))))


(defn substitute-dnf
  [values expr]
  (cond
    (constant? expr) (first (args expr))
    (variable? expr) (get-variable-value values (first (args expr)))
    (negation? expr) (not (substitute-dnf values (first (args expr))))
    (conjunction? expr) (reduce (fn [acc val] (and acc (substitute-dnf values val))) true (args expr))
    (disjunction? expr) (reduce (fn [acc val] (or acc (substitute-dnf values val))) false (args expr))))

(defn substitute-expr [expr values]
  (substitute-dnf values (to-dnf expr)))

(defn -main
  [& args]
  (println "1)  a -> b == !a || !b " (= (disjunction (negation (variable :a)) (variable :b))
                                     (to-dnf (implication (variable :a) (variable :b)))))

  (println "2)  !(a && b && c) == (!a || !b || !c) " (= (disjunction (negation (variable :a)) (negation (variable :b)) (negation (variable :c)))
                                                    (to-dnf (negation (conjunction (variable :a) (variable :b) (variable :c))))))

  (println "3)  !(a || b || c) == (!a && !b && !c) " (= (conjunction (negation (variable :a)) (negation (variable :b)) (negation (variable :c)))
                                                    (to-dnf (negation (disjunction (variable :a) (variable :b) (variable :c))))))

  (println "4)  !!a == a " (= (variable :a)
                           (to-dnf (negation (negation (variable :a))))))

  (println "5)  (a && b && (c || d)) == ((c && a && b) || (d && a && b)) "
           (= (disjunction (conjunction (variable :c) (variable :a) (variable :b)) (conjunction (variable :d) (variable :a) (variable :b)))
              (to-dnf (conjunction (variable :a) (variable :b) (disjunction (variable :c) (variable :d))))))

  (println "6)  (a || b || a) == (a || b ) " (= (disjunction (variable :a) (variable :b))
                                             (to-dnf (disjunction (variable :a) (variable :b) (variable :a)))))

  (println "7)  (a && b && a) == (a && b) " (= (conjunction (variable :a) (variable :b))
                                            (to-dnf (conjunction (variable :a) (variable :b) (variable :a)))))


  (println "8)  !(a -> b) == (a && !b) " (= (conjunction (variable :a) (negation (variable :b)))
                                         (to-dnf (negation (implication (variable :a) (variable :b))))))

  (println "9)  (0 && a) == 0 " (= (constant false)
                                (to-dnf (conjunction (constant false) (variable :a)))))

  (println "10) (1 || a) == 1 " (= (constant true)
                                (to-dnf (disjunction (constant true) (variable :a)))))

  (println "11) ! 0 = 1 " (= (constant true)
                          (to-dnf (negation (constant false)))))

  (println "12) ( 0 -> a) == 1 " (= (constant true)
                                 (to-dnf(implication (constant false) (variable :a)))))

  (println "13) !( 1 || a) == 0 " (= (constant false)
                                        (to-dnf (negation (disjunction (constant true) (variable :a))))))

  (println "14) or (a) = a " (= (variable :a)
                             (to-dnf (disjunction (variable :a)))))
  
  (println "15) and (a) = a " (= (variable :a)
                              (to-dnf (conjunction (variable :a)))))

  (println "16) !((a && (c || b)) && (a && a)) == false, [a=true, b=false, c=true]"
           (false? (substitute-expr (negation (conjunction (conjunction (variable :a) (disjunction (variable :c) (variable :b)))
                                                     (conjunction (variable :a) (variable :a)))) {:a true :b false :c true})))

  (println "17) ((a && !a) || (a ||!a)) == true, [a=true, b=false] "
           (true? (substitute-expr (disjunction (conjunction (variable :a) (negation (variable :a))) (disjunction (variable :a) (negation (variable :a)))) {:a true :b false})))

  (println "18) ((&& 1) || a) == true, [a=true]"
           (true? (substitute-expr (disjunction (conjunction (constant true) ) (variable :a)) {:a true})))

  (println "19) ((!0 && a) || !(a || 0)) == true, [a=true] "
           (true? (substitute-expr(disjunction (conjunction (negation (constant false)) (variable :a)) (negation (disjunction (variable :a) (constant false)))) {:a true})))

  (println "20) ((1 && a) && a) == true, [a=true] "
           (true? (substitute-expr (conjunction (conjunction (constant true) (variable :a)) (variable :a)) {:a true})))

  (println "21) ((0 && a) && a) == false, [a=true] "
           (false? (substitute-expr (conjunction (conjunction (constant false) (variable :a)) (variable :a)) {:a true})))

  (println "22) ((1 || a) || a) == true, [a=false] "
           (true? (substitute-expr (disjunction (disjunction (constant true) (variable :a)) (variable :a)) {:a false})))

  )
