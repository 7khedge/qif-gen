(ns qif-gen.transaction-file
  (:require [clojure.string :as str]))

(defn readTransactions [filepath]
  (str/split-lines (slurp filepath)))

(defn qifFilename [filepath]
  (str/replace filepath #"txt" "qif"))

(defn writeQifTransactions [filepath qiftransactions]
  (with-open [writer (clojure.java.io/writer  filepath )]
    (doseq [transaction qiftransactions]
      (.write writer (str transaction)) qiftransactions)))


