package utils;

public class ValidadorISBN {
    public static boolean validarISBN13(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                int digito = Integer.parseInt(isbn.substring(i, i + 1));
                soma += (i % 2 == 0) ? digito : digito * 3;
            }

            int checksum = 10 - (soma % 10);
            if (checksum == 10) checksum = 0;

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException e) {
            return false;
        }
    }
} 