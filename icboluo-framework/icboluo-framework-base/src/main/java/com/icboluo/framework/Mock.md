mock bean 会给每一个方法一个默认执行策略和返回值

MockBean 和Mock是不同的逻辑，MockBean是将Spring容器中的Bean替换，Mock是生产一个空的Bean

```java
import java.util.ArrayList;

class StudentServiceTest {
    @Mock
    private StudentMapper mapper;
    
    @Mock
    private StudentService service;

    @Captor
    private ArgumentCaptor<Student> studentCaptor;

    /**
     * 有参数，有返回值简单mock
     */
    @Test
    void hava_param_and_return_value() {
        //     准备测试数据
        var query = new Query();
        query.setName("张三");
        // 准备mock数据
        List<String> list = new ArrayList<>();
        //     mock方法
        doReturn(list).when(mapper).selectByQuery(query);
        // when(mapper.selectByQuery(query)).thenReturn(list);
        //     调用被测方法
        var res = service.selectByQuery(query);
        //     验证结果
        assertEquals(0, result.size());
    }

    /**
     * mock 
     */
    @Test
    void mock_static() {
        try (MockedStatic<WebContext> mockedWebContext = mockStatic(WebContext.class, RETURNS_DEEP_STUBS)) {
            mockedWebContext.when(WebContext::userId).thenReturn("user");
            // 验证调用次数
            verify(mapper, times(1)).insert(any());
            // 验证调用次数，并将方法的参数显性出来
            verify(mapper, times(1)).insert(studentCaptor.capture());
            assertEquals("张三", studentCaptor.getValue().getName());
        }
    }

    /**
     *  mock 私有方法
     */
    @Test
    void test_set_area() {
        Method selectByIds = ReflectionUtils.findMethod(StudentService.class, "selectByIds", List.class);
        assert selectByIds != null;
        ReflectionUtils.makeAccessible(selectByIds);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ReflectionUtils.invokeMethod(selectByIds, studentService, list);
        assertEquals('张三', list.get(0).getName());
    }
}
```
