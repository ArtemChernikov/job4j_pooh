package ru.job4j.pooh;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueueServiceTest {

    @Test
    public void whenPostThenGetQueueGoodStatus() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req(Req.POST, "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req(Req.GET, "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo("temperature=18");
        assertThat(result.status()).isEqualTo(Resp.GOOD_STATUS);
    }

    @Test
    public void whenPostGoodStatus() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        Resp result = queueService.process(
                new Req(Req.POST, "queue", "weather", paramForPostMethod)
        );
        assertThat(result.text()).isEqualTo("");
        assertThat(result.status()).isEqualTo(Resp.GOOD_STATUS);
    }

    @Test
    public void whenGetQueueGoodStatus() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req(Req.POST, "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req(Req.GET, "queue", "weather", null)
        );
        assertThat(result.text()).isEqualTo(paramForPostMethod);
        assertThat(result.status()).isEqualTo(Resp.GOOD_STATUS);
    }

    @Test
    public void whenGetQueueBadStatus() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req(Req.POST, "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req(Req.GET, "queue", "unknown", null)
        );
        assertThat(result.text()).isEqualTo("");
        assertThat(result.status()).isEqualTo(Resp.BAD_STATUS);
    }
}