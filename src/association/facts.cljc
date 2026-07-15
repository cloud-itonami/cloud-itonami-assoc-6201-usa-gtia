(ns association.facts
  "Industry rule/policy-statement catalog for the Global Technology
  Industry Association (GTIA, Wikidata Q597534 -- CompTIA's pre-split
  identifier, used as the closest available historical-lineage
  reference) -- a 28th industry-association-level source (see
  cloud-itonami-assoc-6419-jpn-zenginkyo, -6512-jpn-sonpo, -6612-jpn-jsda,
  -6419-deu-bankenverband, -6612-usa-finra, -6512-usa-naic,
  -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf, -6511-jpn-seiho,
  -6910-jpn-nichibenren, -6810-jpn-recaj, -6411-jpn-boj, -6120-usa-ctia,
  -5110-usa-a4a, -3510-usa-eei, -2910-deu-vda, -5510-usa-ahla,
  -2100-usa-phrma, -4719-usa-nrf, -4100-usa-agc, -6020-usa-nab,
  -3600-usa-awwa, -4923-usa-ata, -5610-usa-nra, -2011-usa-acc,
  -8621-usa-ama for the first twenty-seven) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The FIRST entry aligned
  to ISIC 6201 (computer programming activities) -- a new industry
  code for this family. A rule not in this table has NO spec-basis,
  full stop; extend `catalog`, do not invent an id/url/date.

  IMPORTANT DISTINCTION discovered while sourcing: GTIA is the direct
  continuation of the non-profit membership association previously
  known as CompTIA Community / CompTIA (founded 1982 as the Association
  of Better Computer Dealers, 'ABCD'; renamed CompTIA in 1993). In 2025
  the 'CompTIA' brand and its training/certification business (e.g.
  the well-known A+ certification) were SOLD and now operate as a
  separate for-profit company -- GTIA is NOT that entity and does not
  own the CompTIA certifications. This catalog therefore does NOT cite
  any A+/certification material, only documents genuinely published by
  GTIA itself on gtia.org.

  Both entries were directly WebFetch-verified against gtia.org's own
  pages. 'About Us' confirms the 1982 founding year (no month/day
  found, so year-only). 'Code of Conduct' states only 'Updated
  February 26, 2025' -- no original adoption date is given anywhere on
  the page, so :association-rule/established-date is omitted for that
  entry and only :association-rule/last-revised-date is used, rather
  than inventing an original adoption date.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"gtia"
   [{:association-rule/id "gtia.about-us"
     :association-rule/title "About Us"
     :association-rule/association "gtia"
     :association-rule/isic "6201"
     :association-rule/country "USA"
     :association-rule/kind :governance-program
     :association-rule/url "https://gtia.org/about-us"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1982"
     :association-rule/retrieved-at "2026-07-16"
     :association-rule/topic #{:governance}}
    {:association-rule/id "gtia.code-of-conduct"
     :association-rule/title "Code of Conduct"
     :association-rule/association "gtia"
     :association-rule/isic "6201"
     :association-rule/country "USA"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://gtia.org/policies/code-of-conduct"
     :association-rule/url-provenance :official-association-site
     :association-rule/last-revised-date "2025-02-26"
     :association-rule/retrieved-at "2026-07-16"
     :association-rule/topic #{:ethics}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-6201-usa-gtia Wave 0 (ADR-2607141700): "
                 (count (get catalog "gtia")) " gtia entries seeded with an "
                 "official gtia.org citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
