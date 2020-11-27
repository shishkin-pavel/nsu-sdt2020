(ns lab_4.task_4)

(defn variable "Variable constructor."
  [name] {:pre [(keyword? name)]} (list ::var name))

(defn variable? "Check if expr is variable."
  [expr] (= (first expr) ::var))

(defn variable-name "Get variable name."
  [v] (second v))

(defn same-variables? "Check if variables are equal."
  [v1 v2] (and (variable? v1) (variable? v2) (= (variable-name v1) (variable-name v2))))


(defn constant "Constant constructor."
  [vl] {:pre [(boolean? vl)]} (list ::cnt vl))

(defn constant? "Check if expr is constant."
  [expr] (= (first expr) ::cnt))

(defn constant-value "Get constant value."
  [v] (second v))

(defn conjunction "Logical and operation."
  [expr & rest] (cons ::and (cons expr rest)))

(defn conjunction? "Check if expr is and operation."
  [expr] (= (first expr) ::and))

(defn disjunction "Logical or operation."
  [expr & rest] (cons ::or (cons expr rest)))

(defn disjunction? "Check if expr is or operation."
  [expr] (= (first expr) ::or))

(defn implication [expr1 expr2] "Logical implication operation."
  (cons ::impl (list expr1 expr2)))

(defn implication? "Check if expr is implication operation."
  [expr] (= (first expr) ::impl))

(defn negation "Logical not operation."
  [expr] (cons ::neg expr))

(defn negation? "Check if expr is not operation."
  [expr] (= (first expr) ::neg))

(defn base-operation? "Check if expr is base operation."
  [expr] (or (negation? expr) (disjunction? expr) (conjunction? expr)))

(declare calculate)
(declare complex-operations-to-basis)

(defn conjunction-calc [expr vr new-vr]
  (let [res (map #(calculate % vr new-vr) (rest expr))]
    (if (every? #(constant? %) res)
      (constant (every? true? (map constant-value res)))
      (if (some #(and (constant? %) (not (constant-value %))) res)
        (constant false)
        (conjunction res)))))

(defn disjunction-calc [expr vr new-vr]
  (let [res (map #(calculate % vr new-vr) (rest expr))]
    (if (every? #(constant? %) res)
      (constant (boolean (some true? (map constant-value res))))
      (if (some #(and (constant? %) (constant-value %)) res)
        (constant true)
        (disjunction res)))))

(defn implication-calc [expr vr new-vr]
  (let [res (map #(calculate % vr new-vr) (rest expr))]
    (cond
      (every? constant? res) (constant (or (not (constant-value (first res))) (constant-value (second res))))
      (and (constant? (first res)) (not (constant-value (first res)))) (constant true)
      (and (constant? (second res)) (constant-value (second res))) (constant true)
      :else (apply implication res))))

(defn negation-calc [expr vr newvr]
  (let [res (calculate (rest expr) vr newvr)]
    (if (constant? res)
      (constant (not (constant-value res)))
      (negation res))))

(def calc-func
  (atom (list
          [(fn [expr _ _] (constant? expr)) (fn [expr _ _] expr)]
          [(fn [expr vr _] (and (variable? expr) (same-variables? expr vr))) (fn [_ _ newvr] newvr)]
          [(fn [expr _ _] (variable? expr)) (fn [expr _ _] expr)]
          [(fn [expr _ _] (negation? expr)) negation-calc]
          [(fn [expr _ _] (disjunction? expr)) disjunction-calc]
          [(fn [expr _ _] (conjunction? expr)) conjunction-calc]
          [(fn [expr _ _] (implication? expr)) implication-calc])))

(defn calculate
  "replace variable vr with expression new-vr in expression expr and simplify where possible"
  [expr vr new-vr]
  ((some (fn [rule]
           (if ((first rule) expr vr new-vr)
             (second rule)
             false))
         @calc-func)
   expr vr new-vr))

(def convert-rules
  (atom (list
          [(fn [expr] (constant? expr)) (fn [expr] expr)]
          [(fn [expr] (variable? expr)) (fn [expr] expr)]
          [(fn [expr] (negation? expr)) (fn [expr] (negation (complex-operations-to-basis (rest expr))))]
          [(fn [expr] (disjunction? expr)) (fn [expr] (apply disjunction (map #(complex-operations-to-basis %) (rest expr))))]
          [(fn [expr] (conjunction? expr)) (fn [expr] (apply conjunction (map #(complex-operations-to-basis %) (rest expr))))]
          [(fn [expr] (implication? expr)) (fn [expr] (let [res (map #(complex-operations-to-basis %) (rest expr))] (disjunction (negation (first res)) (second res))))])))

(defn complex-operations-to-basis [expr]
  "Convert all logic operation to base operations: and, or, not."
  ((some (fn [rule]
           (if ((first rule) expr)
             (second rule)
             false))
         @convert-rules) expr))

(defn remove-negation [expr]
  "Remove negations from expression."
  (if (base-operation? expr)
    (if (negation? expr)
      (let [nextop (rest expr)]
        (cond
          (negation? nextop) (remove-negation (rest nextop))
          (disjunction? nextop) (apply conjunction (map #(remove-negation (negation %)) (rest nextop)))
          (conjunction? nextop) (apply disjunction (map #(remove-negation (negation %)) (rest nextop)))
          :else (negation nextop)))
      (cons (first expr) (map #(remove-negation %) (rest expr))))

    expr))