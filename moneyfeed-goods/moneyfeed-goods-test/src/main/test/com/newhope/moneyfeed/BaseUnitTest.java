package com.newhope.moneyfeed;


import com.newhope.BootStrap;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootStrap.class)
@WebAppConfiguration
public class BaseUnitTest extends EasyMockSupport {

    @Autowired
    protected WebApplicationContext context;

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    protected MockMvc mockMvc;

    static {
        System.setProperty("spring.cloud.config.label", "RD-DGP1");
        System.setProperty("spring.profiles.active", "automate");
    }

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
