# Primena genetskog algoritma za automatsko igranje Dino igre(offline Google igra)

## Potrebno:

  Leiningen 2.0.0 ili novija verzija

## Koraci za pokretanje aplikacije:

  Aplikacija je okacena na adresi:

## Biblioteke:

  Leiningen - dependency management library for building and configure Clojure project

  Ring - library for building web application in the Clojure programming language

  Compojure - routing library for Ring

## O projektu:

  Primena genetskog algoritma za automatsko igranje Dino igre (offline Google igra)

  Genetski algoritam predstavlja algoritam koji se koristi za optimizaciju i pretrazivanje. Generalni tok algoritma je da se na pocetku odaberu pocetne slucajne      vrednosti koeficijanata (initial population). Zatim se po utvrdjenim kriterijumima za svako resenje (jedinku) odredi koliko dobro resenje predstavlja. Posle toga odredjuju se najbolje i najlosije jedinke iz populacije (selection). Vrsi se generisanje novih jedinki na osnovu najboljih iz prethodne generacije i one zamenjuju najlosije jedinke iz populacije (cross-over). Posto je primeceno da ovakvim tokom algoritma resenja mogu lako konvergirati u lokalni minimum ili maksimum, vrsi se promena koeficijenata (parametara) i to slucajnih (mutation). Algoritam se obicno zavrsava kada rezultat konvergira, tj kada vise nema poboljsanja (termination).

  Za konkretan slucaj Dino igre, genetski algoritam podrazumeva da se uzimaju u obzir sledeci parametri: udaljenost od prepreke, sirina prepreke i brzina igre. To su tri parametra od kojih zavisi skor resenja. Funckija koja odredjuje da li treba skociti ili ne predstavljena je na sledeci nacin:
Y= a1X1 + a2X2 + a3X3 + b
Ukoliko je Y>0 treba skociti, u suprotnom ne treba.

  X1, X2, X3 predstavljaju redom udaljenost od prepreke, sirinu prepreke i brzinu igre. Genetski algoritam varira koeficijente a1,a2,a3 i b kako bi dosao do najboljeg resenja. Svi ovi koeficijenti uzimaju vrednost iz skupa (-1,1)

1) Initial population faza - U ovoj fazi poziva se servis clojure web aplikacije koji odredjuje random pocetne vrednosti za koeficijente.
2) Selection - Uzimaju se dva najbolja resenja iz generacije
3) Cross-over - Od dva najbolja resenja dobijena selekcijom prave se nova dva resenja razmenom koeficijanata roditelja. Granica razmene koeficijenata odredjuje se isto slucajno. Ova dva nova resenja zamenjuju dva resenja sa najlosijim rezultatom na osnovu utvrdjenih kriterijuma.
4) Mutation - Resenjima (jedinkama) se zamenjuju koeficijenti slucajno odredjeni slucajnim vrednostima, kako bi se izbeglo konvergiranje ka lokalnom max/min. U ovom slucaju prilikom mutacije postavljena je veca verovatnoca da se dobije manja vrednost(negativna) jer je primeceno da resenja dosta brze konvergiraju u lokalni maksimum, buduci da je mnogo veca verovatnoca da ce resenje koje ne skace (Y<0) biti losije ocenjeno u vecini slucejeva od onog koje stalno skace (Y>0) jer kod onog (Y>0) postoji verovatnoca da ce ostvariti bolji rezultat (slucajno stici dalje) a resenje cije je Y<0 ce zavrsiti na prvoj prepreci.
