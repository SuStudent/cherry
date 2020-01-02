## 安全模块
> 引入此模块，微服务既受安全控制。其实核心是对资源服务的配置。

需要注意的是**ResourceServerConfigurerAdapter**和**WebSecurityConfigurerAdapter**的优先级问题
默认前者的优先级高于后者，若要更改**@Order**即可，另外，每个配置其实是生成不同的**SecurityFilterChain**而已。
要区分何时使用哪个 SecurityFilterChain，只需要配置http.antMatcher("/demo/**")。这个用来区分什么的URL匹配
什么样的SecurityFilterChain。


