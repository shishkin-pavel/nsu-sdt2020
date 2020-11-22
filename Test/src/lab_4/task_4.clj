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