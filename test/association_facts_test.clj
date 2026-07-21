(ns association-facts-test
  (:require [clojure.java.io :as io] [clojure.java.shell :as shell]
            [clojure.test :refer [deftest is testing]]
            [kotoba.compiler.core :as compiler] [kotoba.compiler.ir :as ir]))
(def source (slurp "src/association_facts.kotoba"))
(defn call [kir f & xs] (ir/execute kir f (vec xs)))
(defn present [x] (when (second x) (nth x 2)))
(def fields ["id" "title" "association" "isic" "country" "kind" "url" "url-provenance"
             "established-date" "last-revised-date" "retrieved-at"])
(def expected
  [{"id" "gtia.about-us" "title" "About Us" "association" "gtia" "isic" "6201" "country" "USA"
    "kind" "governance-program" "url" "https://gtia.org/about-us" "url-provenance" "official-association-site"
    "established-date" "1982" "last-revised-date" nil "retrieved-at" "2026-07-16"}
   {"id" "gtia.code-of-conduct" "title" "Code of Conduct" "association" "gtia" "isic" "6201" "country" "USA"
    "kind" "self-regulatory-code" "url" "https://gtia.org/policies/code-of-conduct"
    "url-provenance" "official-association-site" "established-date" nil
    "last-revised-date" "2025-02-26" "retrieved-at" "2026-07-16"}])
(deftest reference-preserves-authority
  (let [kir (:kir (compiler/compile-source source :js-kotoba-v1))
        observed (mapv (fn [i] (into {} (map (fn [f] [f (present (call kir 'entry-field "gtia" i f))]) fields))) [0 1])]
    (is (= expected observed))
    (is (= [["1982" nil] [nil "2025-02-26"]]
           (mapv (fn [i] (mapv #(present (call kir 'entry-field "gtia" i %)) ["established-date" "last-revised-date"])) [0 1])))
    (is (= ["governance" "ethics"] (mapv #(present (call kir 'topic "gtia" % 0)) [0 1])))
    (is (= "gtia.code-of-conduct" (present (call kir 'by-topic-id "gtia" "ethics" 0))))
    (is (= #{} (set (:effects kir))))
    (testing "fail closed" (is (zero? (call kir 'entry-count "comptia")))
      (is (nil? (present (call kir 'entry-field "gtia" 2 "id"))))
      (is (nil? (present (call kir 'entry-field "gtia" 1 "established-date"))))
      (is (nil? (present (call kir 'topic "gtia" 0 1))))
      (is (zero? (call kir 'by-topic-count "gtia" "certification")))
      (is (nil? (present (call kir 'by-topic-id "gtia" "ethics" 1)))))))
(defn compiler-root [] (nth (iterate #(.getParent ^java.nio.file.Path %)
  (java.nio.file.Path/of (.toURI (io/resource "kotoba/compiler/core.clj")))) 4))
(defn base64 [x] (.encodeToString (java.util.Base64/getEncoder) x))
(deftest restricted-js-and-wasm-conform-semantically
  (let [js (compiler/compile-source source :js-kotoba-v1) wasm (compiler/compile-source source :wasm32-browser-kotoba-v1)
        js64 (base64 (.getBytes ^String (:source js) "UTF-8")) wasm64 (base64 ^bytes (:bytes wasm))
        p (shell/sh "node" "--input-type=module" "-e"
            (str "import(process.argv[1]).then(async h=>{const j=await import('data:text/javascript;base64," js64 "');const w=await h.instantiateKotoba(Buffer.from(process.argv[2],'base64'));const r=x=>{if(x['entry-field']('gtia',0n,'established-date')[2]!=='1982'||x['entry-field']('gtia',1n,'last-revised-date')[2]!=='2025-02-26'||x['entry-field']('gtia',1n,'established-date')[1]!==false)throw Error('dates');if(x['by-topic-id']('gtia','ethics',0n)[2]!=='gtia.code-of-conduct'||x['entry-count']('comptia')!==0n)throw Error('authority');};r(j.instantiateKotoba({}));r(w.instance.exports)}).catch(e=>{console.error(e);process.exit(99)})")
            (.toString (.toUri (.resolve (compiler-root) "runtime/browser-host.mjs"))) wasm64)]
    (is (zero? (:exit p)) (str (:out p) (:err p)))))
(deftest production-source-authority
  (is (= ["src/association_facts.kotoba"] (->> (file-seq (io/file "src")) (filter #(.isFile %)) (map str) sort vec))))
