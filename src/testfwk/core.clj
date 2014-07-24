(ns testfwk.core)

(defmacro spec
  "Execute a specification and reports failure"
  [name & more]
  (cons 'do more))
