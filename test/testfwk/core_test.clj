(ns testfwk.core-test
  (:require [testfwk.core :refer :all] 
             [testfwk.core.bootstrap :refer :all]))

(spec "A Spec describes expected behaviour")

(spec "A Spec runs its body" (eval bootstrap-assertion))

