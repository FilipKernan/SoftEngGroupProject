package model;

import db.PlaylistDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaylistTester {
    private PlaylistDAO plDAO;

    @Test
    public void createPlaylist(){
        Playlist pl = new Playlist("Hello There");
        Assert.assertEquals("Hello There", pl.getName());
    }

    @Test
    public void playlistModelTester(){
        String name = "General Kenobi";
        Playlist pl = new Playlist(name);
        Assert.assertEquals(name, pl.getName());
        Assert.assertNotEquals(null, pl.getID());

        String id2 = "123";
        String name2 = "Hello There";
        Playlist pl2 = new Playlist(id2, name2);
        Assert.assertEquals(name2, pl2.getName());
        Assert.assertEquals(id2, pl2.getID());

        String rename = "You are a bold one";
        pl.renamePlaylist(rename);
        Assert.assertEquals(rename, pl.getName());
        Assert.assertNotEquals(null, pl.getID());

        String rename2 = "Surely you realize you are doomed";
        pl2.renamePlaylist(rename2);
        Assert.assertEquals(rename2, pl2.getName());
        Assert.assertEquals(id2, pl2.getID());

        String name3 = "You fool";
        String id3 = UUID.randomUUID().toString();
        String vidID3 = UUID.randomUUID().toString();
        Playlist pl3 = new Playlist(id3, vidID3, name3);
        Assert.assertEquals(id3, pl3.getID());
        Assert.assertEquals(name3, pl3.getName());
        Assert.assertEquals(vidID3, pl3.getVideoSegmentIDs().get(0));

        String vidID4 = UUID.randomUUID().toString();
        pl3.addVideoSegment(vidID4);
        Assert.assertEquals(pl3.getVideoSegmentIDs().size(), 2);

        Assert.assertEquals(pl3.toString(), id3);

        String vidID5 = UUID.randomUUID().toString();
        String id4 = UUID.randomUUID().toString();
        String name4 = "playlist4";
        Playlist pl4 = new Playlist(id4, vidID5, name4);
        Assert.assertEquals(vidID5, pl4.getVideoSegmentIDs().get(0));
        Assert.assertEquals(1, pl4.getVideoSegmentIDs().size());
        pl4.removeVideoSegment(vidID5);
        Assert.assertNull(pl4.getVideoSegmentIDs());
    }

    @Before
    public void makeDatabase(){
        plDAO = new PlaylistDAO();
    }

    @Test
    public void playlistDB(){
        try{
            System.out.println("Test getting all of the playlists");
            List<Playlist> playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest creating a new playlist and adding it to playlists");
            Playlist newPlaylist = new Playlist("Hello There - Unique Name");
            System.out.println("Newly Created Playlist: \n\tName:" + newPlaylist.getName() + "\n\tID: " + newPlaylist.getID());
            Assert.assertTrue(plDAO.createPlaylist(newPlaylist));
            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest deleting a playlist");
            Assert.assertTrue(plDAO.deletePlaylist(newPlaylist.getID()));
            System.out.println("We will be deleting playlist with name " + newPlaylist.getName() +
                    "and ID " + newPlaylist.getID());
            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    @Test
    public void playlistDBAgain(){
        try{
            System.out.println("Test getting all of the playlists");
            List<Playlist> playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest creating a new playlist and adding it to playlists");
            List<Playlist> addThesePls = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Playlist newPL = new Playlist(UUID.randomUUID().toString());
                addThesePls.add(newPL);
                Assert.assertTrue(plDAO.createPlaylist(newPL));
            }
            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest deleting a playlist");

            for (Playlist pl: addThesePls) {
                Assert.assertTrue(plDAO.deletePlaylist(pl.getID()));
            }

            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTesting it not allowing a duplicate to be added");
            Assert.assertFalse(plDAO.createPlaylist(playlists.get(0)));

            System.out.println("\nTesting it not allowing deleting a playlist with an ID that doesn't exist");
            Assert.assertFalse(plDAO.deletePlaylist(new Playlist("This shouldn't exist").getID()));

        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }

    /* I'm throwing this here for now, the past test case works.
    //TODO Find best method to instanciate environment variables
    You need to mess with the environment variables in order to get it to connect to the database
"C:\Program Files\Java\jdk1.8.0_191\bin\java.exe" -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.4\lib\idea_rt.jar=58973:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.4\lib\idea_rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.4\plugins\junit\lib\junit-rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.2.4\plugins\junit\lib\junit5-rt.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_191\jre\lib\rt.jar;C:\Users\Christopher\IdeaProjects\SoftEngGroupProject\target\test-classes;C:\Users\Christopher\IdeaProjects\SoftEngGroupProject\target\classes;C:\Users\Christopher\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\Christopher\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-lambda-java-events\1.3.0\aws-lambda-java-events-1.3.0.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-s3\1.11.631\aws-java-sdk-s3-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-kms\1.11.631\aws-java-sdk-kms-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-core\1.11.631\aws-java-sdk-core-1.11.631.jar;C:\Users\Christopher\.m2\repository\commons-logging\commons-logging\1.1.3\commons-logging-1.1.3.jar;C:\Users\Christopher\.m2\repository\org\apache\httpcomponents\httpclient\4.5.9\httpclient-4.5.9.jar;C:\Users\Christopher\.m2\repository\org\apache\httpcomponents\httpcore\4.4.11\httpcore-4.4.11.jar;C:\Users\Christopher\.m2\repository\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;C:\Users\Christopher\.m2\repository\software\amazon\ion\ion-java\1.0.2\ion-java-1.0.2.jar;C:\Users\Christopher\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.6.7.2\jackson-databind-2.6.7.2.jar;C:\Users\Christopher\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.6.0\jackson-annotations-2.6.0.jar;C:\Users\Christopher\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.6.7\jackson-core-2.6.7.jar;C:\Users\Christopher\.m2\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-cbor\2.6.7\jackson-dataformat-cbor-2.6.7.jar;C:\Users\Christopher\.m2\repository\joda-time\joda-time\2.8.1\joda-time-2.8.1.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\jmespath-java\1.11.631\jmespath-java-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-sns\1.11.631\aws-java-sdk-sns-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-sqs\1.11.631\aws-java-sdk-sqs-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-cognitoidentity\1.11.631\aws-java-sdk-cognitoidentity-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-kinesis\1.11.631\aws-java-sdk-kinesis-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-java-sdk-dynamodb\1.11.631\aws-java-sdk-dynamodb-1.11.631.jar;C:\Users\Christopher\.m2\repository\com\amazonaws\aws-lambda-java-core\1.1.0\aws-lambda-java-core-1.1.0.jar;C:\Users\Christopher\.m2\repository\mysql\mysql-connector-java\5.1.36\mysql-connector-java-5.1.36.jar" com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 -junit4 model.PlaylistTester,playlistDBAgain
start connecting......
Database has been connected successfully.

Test getting all of the playlists
Playlist1 222222222222222222222222222222222221
Playlist2 222222222222222222222222222222222222
Playlist3 222222222222222222222222222222222223
Playlist4 222222222222222222222222222222222224
Playlist5 222222222222222222222222222222222225

Test creating a new playlist and adding it to playlists
01777422-aa92-40a1-a232-61096202d0af 0ae89a80-4b5d-4118-80d0-c3e9f62dfec1
02534552-8093-4517-9242-7428523a6b5f 8e6e733c-3f6b-4c9a-9a8a-557590d932f5
0623f599-f2bf-4a8c-957a-3d85b5f6ae0a e54250c4-7f84-45dc-ad98-81ee1301726f
0ca76ed4-5151-48c8-9a6a-d4a153a297f2 6d44705c-3d95-4320-a827-8409fb9cad08
0e30856e-5942-4b6d-8b4a-c6da56c56888 330bfd53-514d-4060-b0a4-82fe2633deaa
0ee0fed2-0a97-4290-8812-a2861ac712f6 74e054f7-c5a3-4ea9-834c-369fc958bbbb
0f02a9cd-c1b2-422c-9436-a744ac335743 ab60e1d3-f444-4f9f-b0e6-04d5f367cb15
107a6b5e-451e-4a39-9d13-7ec3eec6ec99 95d4e468-93be-4407-9f46-c949b04df1de
10feea13-32f0-4b06-b311-c0c814d85996 3a2f5fc7-3b86-4137-89cd-2f7c9f7cf85a
123b3e00-b0a8-4524-938e-a7fe5293ae9e 67fb51a5-97e2-4fc9-bfc8-7ebaaab3e242
1a85a9fa-440d-4399-b634-c2b4f36a2a53 d30226e9-d32d-4eb3-bf9f-d96d74d12e5a
1bf57ceb-b962-4e4a-9e08-6a6295b76241 1888f549-7dd9-4703-85af-1c67b5b87922
1f3571cd-38fd-40bb-9bb5-e293d55a5aec de4be6e2-2349-4e1b-a7f2-4e657776d3ec
20571dae-506a-4bae-a484-d5f81873d039 137ee782-481a-4513-8516-1b8b2694d6fe
226c5299-3da4-4bb7-acf7-3f92ee13129a 16b9df43-6d65-4284-893e-a04d8a82bd8f
23f63209-ff8a-468d-b24f-986fade6bbf5 4d04eefa-a69b-404d-982d-91a2aa455b23
28365476-3dec-4f76-a371-e378f1f24f2a 1488e01e-8cdd-4655-9fbc-90c5ad7d737d
2c581025-6617-4870-ae61-29b5f7773cfb 243db9e8-1d24-4a06-aa37-535e0215c213
2cd0e216-9aa0-45bf-a915-9d2f8dbd5eed b428bc66-0c50-422e-aacb-5eb69da2d862
2e2a814b-8a04-4749-bc8a-369306d99f67 8260c75e-89bb-4d9d-8cc2-353d0a358ad6
2f43d45b-e259-4b8e-8022-9086a92f8867 69f7c76c-e52b-4902-93f0-3b7d47f690b5
34c0749b-71ad-4cb3-a664-ad156dfc9d20 f3b266b8-e8ca-4cd8-8252-4ae2135b695a
3a225018-8c55-41a5-aecb-5e51f4265986 79e071c5-a535-46c2-971d-5c38a96fed49
41f29c73-c30c-43b8-8513-ca2256365493 62ed8441-6417-4b5a-b7fd-859d04347bcc
4409b712-6caa-4d3d-97f7-96651b5859e1 48ae6720-f356-45a2-adaf-6b44b792bbdd
441a3513-d634-4091-8f3c-9165c99aa131 e9f7e008-5894-4b10-9462-0e8e6c5e604f
46e07e89-0997-4511-93e9-d9730aa9ecd2 a507dc45-d1fd-42ed-a218-29e5d44a6512
4726d0d8-e373-4159-b775-cc9ded697188 d461f42f-cdc3-4640-94bf-a1484fdbd749
4e24559d-1d41-4f14-b831-a5391222206c 54fc4f49-959c-4ba5-8251-b2db445e15f9
4fccaddd-609f-44a2-86e5-54f51b673084 236b705f-07d1-4693-bf2d-31084eb4b267
5251bbee-a9f7-4792-8833-b3335b984ac2 1816a85b-b402-4bd2-a3e6-1b7e1b32849e
52f0e45b-48fa-4849-a3eb-3c9b823947c6 d92b5a4e-e324-4e3e-9df9-bc8aca6f8900
55635702-0bde-477e-b61f-33bc63eb0acf efab433e-ec69-48b6-b696-2698939eebef
62a1ad0d-74c7-40fd-b4dc-c11e0c21a32f a37aa2cc-8da8-49e7-82e7-f49aa2cee220
68c56cb3-b051-409f-8e45-28aa465a0079 e14aedb1-865d-4ce5-a7f0-0d66d4727887
6bcefca7-b3d6-41cd-bb94-5a255e51af8f d5430cf5-c2e1-40d1-b896-a09d344380f5
6c0b1420-f205-4b2f-ac56-2bb0389d7676 05b2460a-03fc-4e0f-b369-f52639bb3be9
6f0ad0b4-03ab-463c-b867-90ce7263655e 06d0a3ac-8786-433a-8f04-d54e47c01df4
723541eb-3de4-43f5-a58c-c1d1aef05b3a d9bad165-3f86-4e9f-8cec-1f87f9b844c8
726fdec7-dbd5-486d-b1ec-24e70c60f06a 2a3f21e6-328c-4ce2-9c48-4adad976eb77
73f46a9e-895a-473c-bcbd-cc333c9db5c1 ab470552-2b21-4104-8c58-fb3126e34f96
7586775b-b5f8-4f4c-8da6-6d9ba01cfa08 a3d3b192-88ad-4ee3-93aa-5477cf3f971d
792c3158-ab33-42bf-a1b5-031d22fa1298 66103bbf-20a4-4cea-a613-dcecdcbcf5ae
7b5b8420-3224-4a32-9eec-7fd83ef25667 24a4a510-ba2b-4591-8e01-5b56a95143fe
7f385eaf-f151-4236-a630-b967d5895a2c 3e12a184-ed48-47a1-aca4-2e65914b38db
811d8189-d8ef-47d4-9c54-34b75e24ad6e ff8fa7f4-5536-49c4-8440-b833a85bdbd5
89650589-8bb4-4f1e-873b-ed291348a169 cc79ad32-bb26-48c1-aafb-2448303f87c6
8a0cb4fc-7e88-46c7-9508-a3e0cd8d9e9c 7df0278f-411d-4f22-addc-78424cbd5902
8a84b4d7-4652-4df4-b284-52880f259af5 a4eedbf5-76c5-4c27-acd1-73c61d1f8b57
8af13d40-37a1-4083-8306-be56ebb3736b 2eda6ca9-b8a7-4b79-b2a7-b6b0cb54fb80
8f67798f-ec2a-4119-bd75-39b60b76ee1d f47034cb-0bbb-43b3-866e-277cde004498
90feac11-1096-447e-808f-b9c8c0a97ea5 9b4b1c6b-8b92-460c-910c-d4aea86bb4a5
92d62a7f-2b68-4834-9bda-11ed39a2d311 ae966614-5977-4343-9d3e-56a1766aafd8
94f2e2c9-da9a-4627-9e89-b0e4627318c3 a4a6c912-839c-486b-ad05-c76e58ea9d19
95a5dac2-3f20-4ecf-8d0d-1d5f3be8023f 9213be6c-b2a8-4b39-bddb-22155233a300
999faa64-cd1c-47d5-82ce-936944f0e407 a5c1357e-270a-4c20-9f6e-97ccea6f86fc
9a654832-68a4-43a5-bc45-adeaca355b61 63150884-eac5-4a3e-889c-0054edd81bc3
9c320e84-b597-40e5-97c8-619c5615d465 8f00977c-af43-494f-9a26-e83144fcc2a0
9f0991ae-4c90-4409-ac74-692e9de06400 1a9ef077-f8a4-4117-b811-0fe366e64555
9f0aa2b0-ccb2-4fdf-a893-739cbd9bed12 e295cb03-6348-49f3-889f-12e664f1abd1
a0dcdb91-ff00-47b1-98eb-0919cfa437a9 84e5f22d-d26b-4309-a282-aeb8eb7b1c94
a2fdc493-8e7b-46f5-ba41-3708be92b230 bcd26c39-a23d-496f-a00f-738a78033387
a6087504-505a-44d2-88c7-a3a74f96a9e5 50f4821a-70ff-4452-a470-84749a2dd4a2
a7f1777a-62ba-4a15-82d8-381917aeec76 1089afcd-bc57-4103-a26a-b5e54f5f4d33
a8a730ca-3e4a-4100-aff6-2f070e00eea1 38aa6845-8709-4718-a05f-52ac96c4c15e
a9a102b7-a9a5-4113-8c63-238592ce1b89 5838d782-d8de-4668-9542-190d9b6b4163
ab248cff-baf0-420b-86cb-18e88199929b 885450b1-8689-4ef5-9258-88da66c34164
abe69aa3-e59c-4685-828f-1822f79d1fb8 8a405b60-8f0b-4333-9647-53c1b92be748
adb6a962-6320-412f-9bf9-f0933cbae540 76ba5b94-c1a5-495f-9822-f972967cf3ee
aeb18beb-862f-4055-bfe1-98edcc6e49c0 9b49e935-fee5-4caf-993d-5afd2f22b1ea
b1cd9eda-9873-4c48-baee-22199c025630 2c6d9522-564d-4126-8b2c-bc6d968945b6
b422824e-e488-4dd0-8546-0ef19de3b9bf 064a7c1c-6b48-48f7-b303-d48aec1b2a74
b6ea1f7c-7513-4028-b6ec-0ff7831332db 441f1681-1ec5-4025-9f09-2a43c1f7dbd3
be73d4f7-d8b5-4cc3-bf4f-14ffb5618ea3 d5c6f38a-f053-428c-9b3a-59dfbb8b27b0
bf5b3981-0c3a-4805-b180-804c982b0d3b f3bc19ce-aefd-44b9-bc0c-21b932a0e98f
c476b495-2b89-440f-88ce-e3e394fe7d7d d27b022d-f82c-4618-8335-a46a289e5e09
c48a8e12-a11b-4b45-b18d-cdb692a394c0 095da464-6540-4d02-bb4c-af3572c65798
cacbbce5-328b-4611-9ceb-37f7aaee9588 431d947a-7109-46cd-ad2a-d36eb9acb409
cd67dcba-6d27-455b-8398-223362d5eee6 13df884b-6554-4c3d-a8bc-b4d3aa841e1d
ce2887dd-6ee2-47d4-89e6-428b5f84a8e6 dcbd0e88-d53a-4698-8ecc-e2ef113d72c4
ce853a32-e379-4e16-b41d-d16c0872b831 0bfbc056-4538-46c3-8106-0c972d14047f
d3c6506c-adeb-4a51-8a8f-a1b0297b1703 d2b5b419-f54f-4f2f-8ca4-625dac652c28
d3e4b46e-b954-4284-8370-1a47fa23fab5 86b8ba85-d518-470e-8f04-46ddc9e1079e
d3eca254-0328-4446-a220-ff1e814faf69 a652264d-b762-48d2-a97f-bf433e499a58
d5a72a26-f1d1-44d0-b073-1d3b0ac1e39b 72228357-a870-46ed-b75d-55100c15f3ff
d64352f5-4fbc-4743-ae65-69edd179a4bf c735270b-5e9b-4107-adbb-0c91aae713f4
db7803d5-0185-4820-a719-e609da37026b bde16276-4736-4884-98db-a52eee5467ff
e0563384-6c57-4059-9585-7b473e6b7c07 21162896-c96a-4a65-ab58-3ea873de5f3e
e5482392-d797-424a-bc0e-cfb42e8340d3 171c75cc-790f-46cd-8ecc-c2c360102545
e745ef32-57e6-4968-aba5-ff598eac6d93 902a01c0-2e6a-4a7b-b40d-f01d92796083
eb5d5e94-6016-4aad-bd41-f9f4692962ae 69e57454-704d-4c4b-adb7-e30e5a19b97f
f0904b69-141a-4740-960e-21ee139547a4 a8a0c9f5-13ef-46b4-93c0-bc6f16a90d48
f0f0aa3c-7a12-4a78-a113-83b3e923cd11 a8130394-2b10-4e55-999a-f8e3262fd282
f13efb01-5efc-4264-a2b3-6d1e2f855461 1fbdf32e-ee0c-4781-949a-f99ecd70b90d
f4a39d48-8718-4e97-842b-6156ade09580 d71589bb-425a-43f6-adb6-bb504b88ea33
f644af9e-46dc-4b30-b534-738aa0ad1eda a8b0a3c3-178f-4c62-9124-52ec5a184bf1
f8afabfe-271f-4850-aa09-94e407a1bc1f bfd65c08-2393-4875-b3e4-479e6501bf3e
f8b01957-9785-4c0a-8151-06ed6130c19e 8875dfd9-42e9-4fe7-a02e-982e90392d6a
fa64e58a-0f95-4183-a09f-5539ff63d511 2de68468-a3db-4aca-b02d-7b1c54c0b513
fafd099c-4523-4081-8e5c-3462c828b614 5c73e8c0-79ce-43da-bbf5-7915333c2eda
Playlist1 222222222222222222222222222222222221
Playlist2 222222222222222222222222222222222222
Playlist3 222222222222222222222222222222222223
Playlist4 222222222222222222222222222222222224
Playlist5 222222222222222222222222222222222225

Test deleting a playlist
Playlist1 222222222222222222222222222222222221
Playlist2 222222222222222222222222222222222222
Playlist3 222222222222222222222222222222222223
Playlist4 222222222222222222222222222222222224
Playlist5 222222222222222222222222222222222225



Process finished with exit code 0

     */

    }
