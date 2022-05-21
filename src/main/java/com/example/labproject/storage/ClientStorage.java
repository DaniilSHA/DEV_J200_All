package com.example.labproject.storage;

import com.example.labproject.models.Address;
import com.example.labproject.models.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientStorage {

    public static final List<Client> CLIENT_LIST = new ArrayList<>();
    public static int CLIENT_ID_COUNTER = 0;
    public static int ADDRESS_ID_COUNTER = 0;

    static {
        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "мини" ,"222.222.222.222",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Москва",
                                        "Фрунзенская",
                                        22,
                                        66,
                                        11,
                                        "ничего"
                                )
                        ))
        ));

        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "б-34" ,"0.222.2.222",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Москва",
                                        "Фрунзенская",
                                        22,
                                        89,
                                        44,
                                        "что-то"
                                )
                        ))
                ));


        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "б-34" ,"0.0.0.1",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Хабаровск",
                                        "Робинзона",
                                        33,
                                        33,
                                        33,
                                        "что-то"
                                )
                        ))
                ));


        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "мини" ,"222.222.222.222",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Хабаровск",
                                        "Робинзона",
                                        44,
                                        44,
                                        44,
                                        "ничего"
                                )
                        ))
                ));

        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "мини" ,"222.222.222.222",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Хабаровск",
                                        "Всеводолская",
                                        34,
                                        44,
                                        44,
                                        "ничего"
                                )
                        ))
                ));

        CLIENT_LIST.add(
                new Client(++CLIENT_ID_COUNTER, "браузер", "б-34" ,"0.222.2.222",
                        new ArrayList<>(Collections.singletonList(
                                new Address(
                                        ++ClientStorage.ADDRESS_ID_COUNTER,
                                        "Москва",
                                        "Фрунзенская",
                                        22,
                                        31,
                                        54,
                                        "ничего"
                                )
                        ))
                ));
    }
}
