package com.blabas.uzdiz.registry;



/**
 * Created by bozidar on 03.12.2015..
 */
public class RegistryBuilder {

    /**
     * 1. Samo jedan element moze imati zapis s istom sifrom +
     * 2. Jednostavni element mora biti sadrzan u kontejneru koji prethodno mora postojati +
     * 3. Mora postojati samo jedan slozeni element koji ima rod s vlastitom sifrom
     * 4. Slozeni element mora biti prvi
     * 5. Slozeni element mora biti pravokutnik
     * 6. Broj koordinata (paran broj >= 4)
     */

    public static void buildRegistry(String args[], Registry registry){
        registry.setArgs(args);
    }



    public static Registry getRegistry(String args[]){
        Registry registry = new Registry();
        buildRegistry(args, registry);
        return registry;
    }

}
