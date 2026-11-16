# T-017 · Revisión del coordinador

## Revisión final (fork `4e66e34`, 34 cargos) · CERRADO

- **Validador:** 0 errores. Los 7 avisos corresponden a nombres iguales en dos idiomas («Prior», «Superior provincial»…), algo aceptable.
- **Muestreo:** obispo auxiliar (cc. 403-411; vicario general o episcopal, c. 406), vicario general (cc. 475-481), virgen consagrada (c. 604, *Ordo* 1970, *Ecclesiae Sponsae Imago* 2018), viuda consagrada (estatus explicado con precisión), monseñor (*Pontificalis Domus* 1968), catequista instituido (*Antiquum ministerium*) y prelado personal (cc. 294-297). Todo correcto.

### Correcciones del coordinador al integrar

- **Id con errata:** `office_obispo_emperito` pasa a `office_obispo_emerito`, junto con su referencia `after`. Los ids son estables, así que había que corregirlo antes de publicar.
- **Orden del clero:** los cinco cargos de `clergy` se apuntaban entre sí en círculo (`after`: arzobispo mayor → monseñor → capellán → rector → diácono permanente → arzobispo mayor). Se colocaron así:
  - arzobispo mayor, tras patriarca;
  - diácono permanente, tras diácono;
  - rector de seminario, tras párroco;
  - capellán, tras vicario parroquial;
  - monseñor, tras canónigo.
- **Camarlengo:** se retiró «Nombra al vicecamarlengo y es apoyado por tres cardenales asistentes». El vicecamarlengo no lo nombra el camarlengo, y los tres cardenales asistentes (sorteados durante la Sede vacante) proceden de *Universi Dominici Gregis*, no de *Praedicate Evangelium*.
- **Erratas:** «dotarsele» pasa a «dotársele» y «confiararse» a «confiarse».

### Para próximos encargos

- Comprueba que las cadenas de `after` no formen ciclos y apunten a cargos ya existentes del mismo grupo.
- Revisa la ortografía antes del push.
