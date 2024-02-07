<p align="center">
    <img src="https://www.reshot.com/preview-assets/icons/9A4TXY3NUK/loyalty-program-9A4TXY3NUK.svg" alt="Logo" height="350em">
</p>

---
<p align="center">
    <img src="https://forthebadge.com/images/badges/made-with-java.svg"/>
    <img src="https://forthebadge.com/images/badges/license-mit.svg"/><br><br>
   La nostra <strong>Loyalty Platform</strong> è un progetto <b>SpringBoot</b> e <b>Java</b>. Si tratta di una piattaforma per la gestione dei <strong>Programmi Fedeltà</strong>, progettata per l'<b>Università di Camerino</b> corso di <b>Ingegneria del software</b>
</p>

# Indice

1. [Generale](#panoramica)
2. [Sviluppo](#sviluppo)
2. [Framework](#framework)
4. [Progettazione](#progettazione)
5. [Autori](#autori)

---
 # Panoramica generale
 
La piattaforma consente alle aziende di promuovere la propria attività attraverso la creazione di <b>Programmi Fedeltà</b> di diverse tipologie.
Gli <b>Utenti</b> potranno iscriversi a questi <b>Programmi Fedeltà</b> e accumulando punti potranno riscattare premi
I <b>Punti Vendita</b> potranno iscriversi alla piattaforma e mettere in atto alcune strategie per la fidelizzazione degli <b>Utenti</b>, ottenendo informazioni preziose per l'accrescimento dell'azienda.

---
# Sviluppo

Il processo di sviluppo della nostra <b>Loyalty Platform</b> ci serviamo del **`Processo Unificato`**, un processo di sviluppo _Risk Driven_ basato su 3 [Iterazioni](#iterazioni).

### Strumenti di sviluppo:

1. `Visual Paradigm (UML)`
2. `Spring Boot Framework`

La nostra Loyalty Platform ha le seguenti _caratteristiche_:
- `Utente`: Colui che usufruisce dei servizi offerti dalla Loyalty Platform, potrà **Iscriversi** a Programmi Fedeltà e raccogliere i premi ottenuti spendendo nelle attività affiliate.
- `Cassiere`: Il cassiere è un _dipendente_ dell'attività che ha la responsabilità di Registrare Manualmente i nuovi `Utenti`, convalidare i loro _acquisti_ e convalidarli in _punti_ sulle loro `Tessere`.
- `Amministratore`: La figura di `Amministratore` rappresenta un _dipendente_ di una attività con il ruolo di gestione ordinaria della stessa. Ha le manzioni di: *Gestione dei clienti*, *Gestione delle fatture* e fare *Rapporti* sull'andamento del _Punto Vendita_
- `Titolare`: Carica più *importante* nella nostra Loyalty Platform. Il `Titolare` ha la responsabilità di *Gestire* i `Programmi Fedeltà` del suo _Punto Vendita_, *Amministrare* i propri _dipendenti_ e *Gestire* le opzioni di _Marketing_


---
# Framework 

La nostra framework utilizza [Spring Boot](https://spring.io/projects/spring-boot), una framework per lo sviluppo di `API` e `EndPoints`.
Attraverso questa _framework_ abbiamo sviluppato un'architettura a *Micro Servizi* che permette di:
- Evitare punti di guasto
- Architettura più modulare
- Processo Iterativo più rapido
- Scalabilità
- Flessibilità

Questo `Backend` sviluppato in `Java` avrà il compito di gestire le `REST API` della nostra *Loyalty Platform*.
>[!NOTE]
>La sicurezza della piattaforma è garantita dall'utilizzo di **`JWT Tokens`**.

Attraverso `Hibernate e JPA` creiamo delle `Entity` che raffigurano i nostri `Attori` e le loro _caratteristiche_

## Entities Principali
| Entity | Descrizione |
| ------ | ----------- |
| Account | L'`Account` è una classe _astratta_ che descrive le informazioni comuni a tutti gli altri tipi di _`Account`_. |
| Utente | L'`Utente` è la classe che appartiene a tutti i clienti della *Loyalty Platform*, gli `Utenti` potranno ottenere `Tessere` e ottenere `Premi`. |
| Cassiere | Il `Cassiere` è la classe che descrive un dipendente di un `Punto Vendita` con i permessi per Registrare Manualmente `Utenti` e convalidare _Acquisti_ su `Tessere`. |
| Amministratore | L'`Amministratore` è una classe importante per la ***Gestione*** ordinaria di un `Punto Vendita`. Potrà visualizzare le `Fatture`, i `Tesserati` ad un `Programma Fedeltà`, gestire gli `Utenti` e le loro _fidelizzazioni_. |
| Titolare | Il `Titolare` è una classe che specifica un `Account` con permessi di gestione totale su `Punti Vendita`, `Dipendenti`, `Programmi Fedeltà`, `Utenti`, `Tesseramenti` e `Collaborazioni`. |

| Entity | Descrizione |
| --------- | ----------- |
| Programma Fedeltà | Il `Programma Fedeltà` è una classe _astratta_ che descrive le informazioni comuni a tutti i tipi di _`Programma Fedeltà`_. |
| Programma Punti | Il `Programma Punti` è una classe che descrive un _`Programma Fedeltà`_ con strategia *Punti*, gli `Utenti` tesserati a questo programma riceveranno _Punti_ per i loro acquisti. |
| Programma Livelli | Il `Programma Livelli` è una classe che descrive un _`Programma Fedeltà`_ con strategia *Livelli*, gli `Utenti` tesserati a questo programma riceveranno _Esperienza_ e progrediranno nei `Livelli` stabiliti sul _`Programma Fedeltà`_ |
| Programma VIP | Il `Programma VIP` è una classe che descrive un _`Programma Fedeltà`_ con strategia *VIP*, gli `Utenti` che desiderano tesserarsi a questo programma pagheranno una somma di _iscrizione_ fissa e riceveranno _premi_ dedicati |
| Programma Cashback | Il `Programma Cashback` è una classe che descrive un _`Programma Fedeltà`_  con strategia *Cashback*, gli `Utenti` tesserati a questo programma riceveranno indietro una percentuale del _Saldo_ speso sul `Punto Vendita` |
| Programma Coalizione | Il `Programma Coalizione` è una classe che descrive un _`Programma Fedeltà`_ con strategia *Coalizione*, gli `Utenti` tesserati a questo programma avranno sconti e coupon su entrambi i `Punti Vendita` coalizzati nel _programma_ |

| Entity | Descrizione |
| ------ | ----------- |
| Punto Venditra | Il `Punto Vendita` è la classe che descrive l'*Attivitò* registrata da un `Titolare`, contiene le informazioni principali del `Punto Vendita` |
| Tessera | La `Tessera` è la classe che descrive la fidelizzazione tra un `Utente` ed un `Programma Fedeltà`. Contiene le informazioni dell'`Utente`, del `Programma Fedeltà` e il _*Saldo*_ disponibile su quella `Tessera` |

## Entities Secondarie

| Entity | Descrizione |
| ------ | ----------- |
| Fattura | La `Fattura` è una classe utilizzata per generare *PDF* con informazioni sulle spese mensili del `Punto Vendita` |
| Premio | Il `Premio` è una classe utilizzata per descrivere un premio riscattabile da un `Utente` in base alle strategie dei `Programmi Fedeltà` |

## API
Ogni `Account` ha la possibilità di ricevere i seguenti `Permessi`:
- `PF_AUTH`: Permette all'`Account` di gestire i `Programmi Fedeltà`
- `PV_AUTH`: Permette all'`Account` di gestire il `Punto Vendita`
- `USER_CREATE_AUTH`: Permette all'`Account` di registrare altri `Account`
- `USER_REMOVE_AUTH`: Permette all'`Account` di rimuovere altri `Account`
- `TESSERE_VIEW`: Permette all'`Account` di visualizzare le `Tessere` di ogni `Programma Fedeltà`
- `SUBSCRIBE_AUTH`: Permette all'`Account` di iscriversi a `Programmi Fedeltà` e ottenere cosi nuove `Tessere`
- `UNSUBSCRIBE`: Permette all'`Account` di rimuovere l'iscrizione ad un `Programma Fedeltà`
- `PV_SEARCH`: Permette all'`Account` di effettuare ricerche di `Punti Vendita`
- `INFO_VIEW`: Permette all'`Account` di visualizzare le informazioni sul proprio `Account`
- `INFO_UPDATE`: Permette all'`Account` di aggiornare i propri _Dati_
- `DIPENDENTI_VIEW`: Permette all'`Account` di visualizzare una lista di clienti del `Punto Vendita`


La nostra **API** ha i seguenti `EndPoint`:
>[!IMPORTANT]
>Questi ENDPOINT sono whitelisted e non sono coperti dalla *Spring Security*

| ENDPOINT | Descrizione |
| -------- | ----------- |
| `POST /dashboard/auth/authenticate` | Autenticazione degli `Account` |
| `GET /dashboard/healthcheck` | Healthcheck del server |
| `POST /dashboard/newutente` | Registrazione dei un nuovo `Utente` |

>[!CAUTION]
>Questi ENDPOINT sono protetti da *Spring Security* e richiedono un *Token Valido*

| ENDPOINT | Descrizione |
| -------- | ----------- |
| `POST /dashboard/newprogram` | Creazione di `Programmi Fedeltà` |
| `PUT /dashboard/editprogram` | Modifica delle caratteristiche del `Programma Fedeltà` |
| `POST /dashboard/newpuntovendita` | Creazione di `Punti Vendita` |
| `DELETE /dashboard/removeutente` | Rimozione di un `Account` |
| `GET /dashboard/viewiscritti` | Mostra le `Tessere` associate ad un `Programma Fedeltà` |
| `POST /dashboard/newtessera` | Iscrizione ad un `Programma Fedeltà` |
| `GET /dashboard/viewpuntovendita` | Ricerca di `Punti Vendita` |
| `GET /dashboard/viewinfo` | Visualizza informazioni di `Account` |
| `DELETE /dashboard/removetessera` | Rimuovi una `Tessera` |
| `PUT /dashboard/updateaccount` | Aggiorna le informazioni di un `Account` |
| `GET /dashboard/viewdipendenti` | Visualizza i dipendenti di un `Punto Vendita` |
| `GET /dashboard/viewfattura/{id}` | Visualizza una singola `Fattura` |
| `GET /dashboard/viewfatture/{id}` | Visualizza tutte le `Fatture` di un `Punto Vendita` |
| `GET /dashboard/viewcatalogo` | Visualizza un catalogo di `Premi` |

La persistenza delle nostre `Entities` avviene tramite `Hibernate` su un database [`MySQL`](https://www.mysql.com)

## Build

Per il `Building` del nostro _backend_ utilizziamo [`Gradle`](https://gradle.org), un potente strumento di *Building* automatico.
### Prerequisiti
* clonare la repository ```https://github.com/Francesco-Bonifazi/Loyalty-Platform.git```
* scaricare [Gradle Build Tool](https://gradle.org/)

### Installazione
1. Aprire il file  ```loyaltyplatform\src\main\resources\application.properties``` ed inserire le informazioni pertinenti al vostro `Environemtn`
2. Posizionarsi sulla cartella ```loyaltyplatform\>```
3. Eseguire il build
   ```sh
   .\gradlew bootRun
   ```

---
# Progettazione
L'utilizzo del `Processo Unificato` insieme a `Visual Paradigm` e al linguaggio `UML` consente una _progettazione_ e una _gestione_ del *ciclo di vita* del software strutturata, collaborativa ed efficiente, aiutando a garantire la consegna di prodotti software di alta qualità.

## Iterazioni:

Nel nostro progetto sono state _sviluppate_ 3 `Iterazioni` composte da :
- Diagramma Casi d'Uso
- Diagramma classi di Analisi
- Diagramma classi di Progetto
- Diagramma di sequenza

Questi diagrammi sono stati creati a *cascata* per ogni `Iterazione` sviluppando iterativamente i contenuti costruiti sulle `Iterazioni` precedenti.

### **1° Iterazione**

Nella prima iterazione ci siamo concentrati sulla *Scoperta* delle funzionalità della nostra *Applicazione* cercando di _individuarne_ l'80%/90%
In seguito alla *Scoperta* abbiamo implementato una `Baseline eseguibile` molto rudimentale.

### **2° Iterazione**

Nella seconda iterazione abbiamo costruito sui `Casi d'uso` della prima `Iterazione` inserendone di nuovi.
Abbiamo rivisitato il `Diagramma classi di Analisi` inserendo nuove `Classi` e riorganizzando gli elementi `UML` per renderli più visualmente comprensibili
In seguito abbiamo implementato con maggior *precisione* le `Classi` più _importanti_ iniziando cosi a formare una struttura per la nostra `Framework SpringBoot`

### **3° Iterazione**

Nella terza iterazione abbiamo inserito i `Casi d'uso` finali per costruire un prodotto più _completo_.
Abbiamo finalizzato il `Diagramma classi di Analisi` inserendo le ultime funzionalità e rivisitando le precedenti come forma di `Verifica`
L'implementazione del `Backend` viene finalizzata, la `Baseline` eseguibile è stata completata e tutti i `Diagrammi` sono stati *Verificati*

---
# Autori
- [Francesco Bonifazi](https://github.com/Francesco-Bonifazi)
- [Vittorio Tidei](https://github.com/VittorioTidei)
