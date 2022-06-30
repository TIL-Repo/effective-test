package me.hajoo.monkey;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.customizer.ExpressionSpec;

class UserTest {

	private static final FixtureMonkey fixtureMonkey = FixtureMonkey.create();

	@RepeatedTest(100)
	void start() throws Exception {
		// when
		User user = fixtureMonkey.giveMeOne(User.class);

		// then
		assertAll(
			() -> assertThat(user.getId()).isNotNull(),
			() -> assertThat(user.getUsername()).isNotBlank(),
			() -> assertThat(user.getPassword()).isNotBlank(),
			() -> assertThat(user.getName()).isNotBlank(),
			() -> assertThat(user.getState()).isNotNull(),
			() -> assertThat(user.getBirth()).hasSize(8).isNotNull(),
			() -> assertThat(user.getHobbies()).hasSizeLessThanOrEqualTo(4)
		);
	}
	
	@RepeatedTest(100)
	void set() throws Exception {
		// when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("name", "홍길동")
			.set("address", "서울특별시 관악구")
			.set("state", User.UserState.JOIN)
			.sample();

		// then
		assertAll(
			() -> assertThat(user.getId()).isNotNull(),
			() -> assertThat(user.getUsername()).isNotBlank(),
			() -> assertThat(user.getPassword()).isNotBlank(),
			() -> assertThat(user.getName()).isEqualTo("홍길동"),
			() -> assertThat(user.getAddress()).isEqualTo("서울특별시 관악구"),
			() -> assertThat(user.getState()).isEqualTo(User.UserState.JOIN),
			() -> assertThat(user.getBirth()).hasSize(8).isNotNull(),
			() -> assertThat(user.getHobbies()).hasSizeLessThanOrEqualTo(4)
		);
	}

	@RepeatedTest(100)
	void setByExpressionSpec() throws Exception {
		// when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set(new ExpressionSpec()
				.set("name", "홍길동")
				.set("address", "서울특별시 관악구")
				.set("state", User.UserState.JOIN))
			.sample();

		// then
		assertAll(
			() -> assertThat(user.getId()).isNotNull(),
			() -> assertThat(user.getUsername()).isNotBlank(),
			() -> assertThat(user.getPassword()).isNotBlank(),
			() -> assertThat(user.getName()).isEqualTo("홍길동"),
			() -> assertThat(user.getAddress()).isEqualTo("서울특별시 관악구"),
			() -> assertThat(user.getState()).isEqualTo(User.UserState.JOIN),
			() -> assertThat(user.getBirth()).hasSize(8).isNotNull(),
			() -> assertThat(user.getHobbies()).hasSizeLessThanOrEqualTo(4)
		);
	}

	@Test
	void setFixObject() throws Exception {
	    // when
		String result = fixtureMonkey.giveMeBuilder(String.class).sample();

		// then
		assertThat(result).isInstanceOf(String.class);
	}

	@Test
	void setNull() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.setNull("address")
			.sample();

		// then
		assertThat(user.getAddress()).isNull();
	}

	@Test
	void setNotNull() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.setNotNull("address")
			.sample();

		// then
		assertThat(user.getAddress()).isNotNull();
	}

	@RepeatedTest(100)
	void injectRandomValue() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("id", Arbitraries.longs().between(1, 100))
			.sample();

		// then
		assertThat(user.getId()).isBetween(1L, 100L);
	}

	@RepeatedTest(100)
	void setPostConditionByField() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.set("id", Arbitraries.longs().between(1, 50))
			.setPostCondition("id", Long.class, it -> it < 10)
			.sample();

		// then
		assertThat(user.getId()).isBetween(1L, 10L);
	}

	@RepeatedTest(100)
	void setPostConditionByObject() throws Exception {
		// when
		String result = fixtureMonkey.giveMeBuilder(String.class)
			.setPostCondition(it -> it.length() > 5)
			.sample();

		// then
		assertThat(result).hasSizeGreaterThanOrEqualTo(5);
	}

	@Test
	void customize() throws Exception {
		// when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.customize(User.class, u -> {
				u.setAddress("address");
				return u;
			})
			.sample();

		// then
		assertThat(user.getAddress()).isEqualTo("address");
	}

	@RepeatedTest(30)
	void setCollectionSize() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.size("subjects", 5)
			.sample();

		// then
		assertThat(user.getSubjects()).hasSize(5);
	}

	@RepeatedTest(30)
	void setCollectionMinSize() throws Exception {
	    // when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.minSize("subjects", 5)
			.sample();

		// then
		assertThat(user.getSubjects()).hasSizeGreaterThanOrEqualTo(5);
	}

	@RepeatedTest(30)
	void setCollectionMaxSize() throws Exception {
		// when
		User user = fixtureMonkey.giveMeBuilder(User.class)
			.maxSize("subjects", 5)
			.sample();

		// then
		assertThat(user.getSubjects()).hasSizeLessThanOrEqualTo(5);
	}
}