# Business model — DJI

Independent Public-Sector Market-Entry & Procurement Compliance Service
for the Republic of Djibouti: an assistive actor that helps a market-
entry operator assemble, track, and (with a human's sign-off) file the
registration evidence a foreign operator needs to bid on Djiboutian
public tenders.

## Who this serves

Foreign companies (and their local counsel/agents) preparing to bid on
Djiboutian public-sector contracts, who need to track:

- **RCCM registration** (Registre du Commerce et du Crédit Mobilier) —
  Djibouti's commercial/business registration record, administered
  under the OHADA (Organisation pour l'Harmonisation en Afrique du
  Droit des Affaires) treaty framework via the CFE (Centre de
  Formalités des Entreprises) single administrative window.
- **CNMP tender-dossier conformity** — the Commission Nationale des
  Marchés Publics examines pre-qualification and tender dossiers for
  conformity BEFORE a call for competition is launched.
- **Tribunal de Commerce filings** — share capital, director/
  shareholder compliance, and UBO (Ultimate Beneficial Owner)
  disclosure, required for ALL registered entity types.
- **BOAMP** — the Bulletin Officiel des Annonces de Marchés Publics,
  the official procurement-announcement bulletin CNMP maintains on
  `marchespublics.gouv.dj`.

Entry paths a foreign operator may use per the dossier: incorporate a
local company (a foreign national may serve as sole director with no
residency/local-work-permit requirement tied specifically to that
role), or enter via a Branch or Representative Office without
establishing a separate legal entity. Whichever path is used, Djibouti
requires share capital, director/shareholder compliance, UBO
disclosure, and KYC documents filed with the Tribunal de Commerce.

## What this actor does

1. **Engagement intake** — normalize the operator's own case data
   (operator name, engagement fee terms). No new facts invented.
2. **Jurisdiction assessment** — hand back the DJI evidence checklist
   from `src/marketentry/facts.cljc`, always citing an official source
   (`marchespublics.gouv.dj`, `rccm.ohada.org`). A jurisdiction not in
   the catalog gets NO checklist — the actor states plainly that it has
   no official spec-basis rather than guessing.
3. **Filing draft** — prepare the FILING-DRAFT record (an unsigned,
   internal book-of-record entry — not a real CNMP/BOAMP submission).
4. **Filing submit** — prepare the FILING-SUBMIT record. This is the
   step that corresponds to an actual real-world portal action, so it
   is the most tightly gated.

## Trust Controls

- **A false or fabricated regulatory-requirement claim is a HARD
  hold.** Every jurisdiction assessment must cite an official source
  from `marketentry.facts`; an assessment with no citation, or one that
  claims a `:spec-basis` this actor never verified, is rejected outright
  and no human can override it.
- **Any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off.** `:filing/draft` and `:filing/submit` proposals are
  NEVER auto-committed, at any rollout phase — a human market-entry
  operator makes the actual filing decision every time, even when the
  governor finds nothing wrong.
- **Independent re-verification, not trust-on-claim.** The governor
  independently recomputes the claimed engagement fee (`base-fee +
  monthly-rate × monitoring-months`) rather than trusting the claimed
  total, and independently checks the engagement's own
  `:has-rccm-entity?` / `:rccm-registration-verified?` facts rather than
  assuming a filing-draft or filing-submit proposal is accurate.
- **No invented national e-procurement portal.** The dossier documents
  only the BOAMP bulletin (`marchespublics.gouv.dj`) as a public
  procurement announcement channel — there is no verified evidence of a
  transactional national e-procurement portal comparable to, say,
  Angola's SNCP or the USA's SAM.gov, and this actor does not claim one.
- **Append-only audit ledger.** Every commit or hold decision writes
  exactly one immutable ledger fact — there is a complete, tamper-evident
  record of what was proposed, what the governor found, and what a human
  approved or rejected.

## Regulatory sources (all independently verified)

- CNMP (Commission Nationale des Marchés Publics):
  `https://marchespublics.gouv.dj/`
- Procurement Code: Loi n°53/AN/09/6ème L portant Code des Marchés
  Publics (1 July 2009) — `https://marchespublics.gouv.dj/reglement/24`
  (also indexed at ILO NATLEX:
  `https://ilo.org/dyn/natlex/natlex4.detail?p_isn=81976&p_lang=fr`)
- CNMP mandate/composition: Décret N° 2010-0083/PRE (modifié par
  Décret N° 2020-198/PRE) — `https://marchespublics.gouv.dj/reglement/17`
- RCCM / OHADA regional registry: `https://rccm.ohada.org/`,
  `https://rccm.ohada.org/staticPage/index?alias=rccm`
