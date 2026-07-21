# cloud-itonami-assoc-6201-usa-gtia

Industry rule/policy-statement catalog for the **Global Technology
Industry Association** (GTIA) — the FIRST entry aligned to **ISIC
6201** (computer programming activities), a new industry code for this
family.

> **Note:** GTIA is the direct continuation of the non-profit
> membership association previously known as CompTIA Community /
> CompTIA (founded 1982 as the Association of Better Computer Dealers,
> "ABCD"; renamed CompTIA in 1993). In 2025 the "CompTIA" brand and its
> training/certification business (including the well-known A+
> certification) were **sold** and now operate as a separate for-profit
> company — GTIA is **not** that entity and does not own the CompTIA
> certifications. This catalog cites only documents genuinely published
> by GTIA itself.

Alongside
[`cloud-itonami-assoc-6419-jpn-zenginkyo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-jpn-zenginkyo),
[`-6512-jpn-sonpo`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-jpn-sonpo),
[`-6612-jpn-jsda`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-jpn-jsda),
[`-6419-deu-bankenverband`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-deu-bankenverband),
[`-6612-usa-finra`](https://github.com/cloud-itonami/cloud-itonami-assoc-6612-usa-finra),
[`-6512-usa-naic`](https://github.com/cloud-itonami/cloud-itonami-assoc-6512-usa-naic),
[`-6920-jpn-jicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-jpn-jicpa),
[`-6920-usa-aicpa`](https://github.com/cloud-itonami/cloud-itonami-assoc-6920-usa-aicpa),
[`-6419-fra-fbf`](https://github.com/cloud-itonami/cloud-itonami-assoc-6419-fra-fbf),
[`-6511-jpn-seiho`](https://github.com/cloud-itonami/cloud-itonami-assoc-6511-jpn-seiho),
[`-6910-jpn-nichibenren`](https://github.com/cloud-itonami/cloud-itonami-assoc-6910-jpn-nichibenren),
[`-6810-jpn-recaj`](https://github.com/cloud-itonami/cloud-itonami-assoc-6810-jpn-recaj),
[`-6411-jpn-boj`](https://github.com/cloud-itonami/cloud-itonami-assoc-6411-jpn-boj),
[`-6120-usa-ctia`](https://github.com/cloud-itonami/cloud-itonami-assoc-6120-usa-ctia),
[`-5110-usa-a4a`](https://github.com/cloud-itonami/cloud-itonami-assoc-5110-usa-a4a),
[`-3510-usa-eei`](https://github.com/cloud-itonami/cloud-itonami-assoc-3510-usa-eei),
[`-2910-deu-vda`](https://github.com/cloud-itonami/cloud-itonami-assoc-2910-deu-vda),
[`-5510-usa-ahla`](https://github.com/cloud-itonami/cloud-itonami-assoc-5510-usa-ahla),
[`-2100-usa-phrma`](https://github.com/cloud-itonami/cloud-itonami-assoc-2100-usa-phrma),
[`-4719-usa-nrf`](https://github.com/cloud-itonami/cloud-itonami-assoc-4719-usa-nrf),
[`-4100-usa-agc`](https://github.com/cloud-itonami/cloud-itonami-assoc-4100-usa-agc),
[`-6020-usa-nab`](https://github.com/cloud-itonami/cloud-itonami-assoc-6020-usa-nab),
[`-3600-usa-awwa`](https://github.com/cloud-itonami/cloud-itonami-assoc-3600-usa-awwa),
[`-4923-usa-ata`](https://github.com/cloud-itonami/cloud-itonami-assoc-4923-usa-ata),
[`-5610-usa-nra`](https://github.com/cloud-itonami/cloud-itonami-assoc-5610-usa-nra),
[`-2011-usa-acc`](https://github.com/cloud-itonami/cloud-itonami-assoc-2011-usa-acc),
and
[`-8621-usa-ama`](https://github.com/cloud-itonami/cloud-itonami-assoc-8621-usa-ama).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on GTIA's behalf.

Coverage is reported honestly through the bounded `association-facts` ABI: an
association not admitted by `association-covered?` has **no spec-basis**, full
stop — never fabricate one.

## Data

- `src/association_facts.kotoba` — the sole production catalog source.
- `schema/association-rule.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

Both entries were directly WebFetch-verified against
[gtia.org](https://gtia.org/)'s own pages: **About Us** (confirms the
1982 founding as "ABCD", renamed CompTIA in 1993, separated as GTIA in
2025) and **Code of Conduct** (states only "Updated February 26,
2025" — no original adoption date is given anywhere on the page, so
`:association-rule/established-date` is omitted and only
`:association-rule/last-revised-date` is used).

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Policy text
itself remains GTIA's; this repo stores only citation metadata
(id/title/url/dates), not full text.
