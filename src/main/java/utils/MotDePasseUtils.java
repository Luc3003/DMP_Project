package utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MotDePasseUtils {
  private static final int LONGUEUR_SEL = 128;
  private static final int LONGUEUR_HASH = 128;

  private static final int NOMBRE_ITERATIONS = 5;
  private static final int MEMOIRE = 65536;
  private static final int PARALLELISME = 1;

  private static Argon2 instancierArgon2() {
    return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i, LONGUEUR_SEL, LONGUEUR_HASH);
  }

  public static String genererMotDePasse(String motDePasse) {
    return instancierArgon2().hash(NOMBRE_ITERATIONS, MEMOIRE, PARALLELISME, motDePasse);
  }

  public static boolean validerMotDePasse(String motDePasse, String hashCorrect) {
    return instancierArgon2().verify(hashCorrect, motDePasse);
  }

  /*public static void main(String[] args) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateborn = LocalDate.parse("2021-10-12", dateFormat);
    LocalDate now = LocalDate.now();
    System.out.println(now);
    System.out.println(dateborn);
    System.out.println();
  }*/

}