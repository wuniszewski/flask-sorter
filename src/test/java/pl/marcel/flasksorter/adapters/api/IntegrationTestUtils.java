package pl.marcel.flasksorter.adapters.api;

import java.util.List;
import java.util.UUID;

class IntegrationTestUtils {

    static final UUID EXISTING_FLASK_ID = UUID.fromString("381ed7a9-579c-4fa0-8f36-23c8a562699b");

    static final UUID NON_EXISTING_FLASK_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");

    static final List<UUID> TWO_IDENTICAL_ONLY_AGE_FLASKS_IDS = List.of(
            UUID.fromString("381ed7a9-579c-4fa0-8f36-23c8a562699b"),
            UUID.fromString("b73ab205-db62-4282-851a-9d2fff37c380"));

    static final List<UUID> TWO_IDENTICAL_ONLY_COMPANY_NAME_FLASKS_IDS = List.of(
            UUID.fromString("b73ab205-db62-4282-851a-9d2fff37c380"),
            UUID.fromString("00fc266b-ac1d-4a09-8f1e-1287c077cb68"));

    static final List<UUID> TWO_IDENTICAL_ONLY_CITY_DISTRICT_FLASKS_IDS = List.of(
            UUID.fromString("00fc266b-ac1d-4a09-8f1e-1287c077cb68"),
            UUID.fromString("7c557e4e-c31e-4583-a5b0-1ccb805237c2"));

    static final List<UUID> TWO_IDENTICAL_ONLY_VISION_DEFECT_FLASKS_IDS = List.of(
            UUID.fromString("7c557e4e-c31e-4583-a5b0-1ccb805237c2"),
            UUID.fromString("70f79ce9-32a8-4e80-9a83-49c267b9cd55"));

    static final List<UUID> THREE_IDENTICAL_FLASKS_IDS = List.of(
            UUID.fromString("879f2fe8-1c48-4e8f-bb2b-06031b363f95"),
            UUID.fromString("e0f535b0-df5a-4d66-9925-530d695498bc"),
            UUID.fromString("b1a6980d-1f8f-4950-84ab-5cc5e61081ab"));
}


