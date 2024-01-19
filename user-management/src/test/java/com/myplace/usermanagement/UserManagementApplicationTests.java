package com.myplace.usermanagement;


import com.myplace.usermanagement.entity.EnumClasses.Interests;
import com.myplace.usermanagement.entity.EnumClasses.Languages;
import com.myplace.usermanagement.entity.EnumClasses.Role;
import com.myplace.usermanagement.entity.EnumClasses.Sports;
import com.myplace.usermanagement.entity.Users.Member;
import com.myplace.usermanagement.entity.Users.MemberInfo;
import com.myplace.usermanagement.repositories.AccountManagementDAO;
import com.myplace.usermanagement.repositories.ProfileManagementDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class UserManagementApplicationTests {
	@Autowired
	private AccountManagementDAO accountManagementDAO;
	@Autowired
	private ProfileManagementDAO profileManagementDAO;
	UUID uuid = UUID.randomUUID();

	@Test
	void contextLoads() {
		UUID uuid = UUID.randomUUID();
		MemberInfo memberInfo = MemberInfo.builder()
				.userId(uuid)
				.country("United States")
				.phoneNumber("123456789")
				.state("California")
				.city("San Francisco")
				.studies("Computer Science")
				.bio("Passionate about technology")
				.whatILove("Coding")
				.curiousFactAboutMe("I can play multiple musical instruments")
				.profession("Software Engineer")
				.favoriteSong("Bohemian Rhapsody")
				.pets("Cat")
				.dob(new Date())
				.languagesStorage(Set.of(Languages.ENGLISH, Languages.SPANISH))
				.sportsStorage(Set.of(Sports.AMERICAN_FOOTBALL, Sports.ATHLETICS))
				.interestsStorage(Set.of(Interests.Cooking, Interests.Cycling))
				.build();
		profileManagementDAO.save(memberInfo);
		Member member =
			Member.builder()
				.userId(uuid)
				.username("taveriton")
				.memberInfo(memberInfo)
				.email("emersontiven1@gmail.com")
				.firstName("Emerson")
				.lastName("Tavera")
				.roles(Set.of(Role.USER, Role.ADMIN))
				.build();

		var t = accountManagementDAO.save(member);
    System.out.println(t.getUserId().toString());
	}

	@Test
	public void saveJustOneMember(){
		Member member =
				Member.builder()
						.userId(this.uuid)
						.username("taveriton")
						.email("emersontiven1@gmail.com")
						.firstName("Emerson")
						.lastName("Tavera")
						.roles(Set.of(Role.USER, Role.ADMIN))
						.build();
		accountManagementDAO.save(member);
	}

	@Test
	public void saveTheMemberInfoSeparately(){
    Member member = accountManagementDAO.findByEmail("emersontiven1@gmail.com");
		MemberInfo memberInfo = MemberInfo.builder()
				.userId(member.getUserId())
				.country("United States")
				.phoneNumber("123456789")
				.state("California")
				.city("San Francisco")
				.studies("Computer Science")
				.bio("Passionate about technology")
				.whatILove("Coding")
				.curiousFactAboutMe("I can play multiple musical instruments")
				.profession("Software Engineer")
				.favoriteSong("Bohemian Rhapsody")
				.pets("Cat")
				.dob(new Date())
				.languagesStorage(Set.of(Languages.ENGLISH, Languages.SPANISH))
				.sportsStorage(Set.of(Sports.AMERICAN_FOOTBALL, Sports.ATHLETICS))
				.interestsStorage(Set.of(Interests.Cooking, Interests.Cycling))
				.build();
		member.setMemberInfo(memberInfo);
		accountManagementDAO.save(member);

	}

}
