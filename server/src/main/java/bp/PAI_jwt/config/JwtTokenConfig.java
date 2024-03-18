package bp.PAI_jwt.config;

//Tydzień 1, Wzorzec Singleton
//Klasa JwtTokenConfig zapewnia przechowywanie i dostęp do czasu ważności tokenów JWT.
// Jest to klasa singletonowa, dostępna poprzez metodę statyczną getInstance
public final class JwtTokenConfig {
    private static JwtTokenConfig instance;

    public long JWT_TOKEN_VALIDITY;

    private JwtTokenConfig(long value) {
        this.JWT_TOKEN_VALIDITY = value;
    }

    public static JwtTokenConfig getInstance(long value) {
        if (instance == null) {
            instance = new JwtTokenConfig(value);
        }
        return instance;
    }
}
//Koniec, Tydzień 1, Wzorzec Singleton