package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.FreiGalvaoIntention
import com.example.data.model.FreiGalvaoMiracle
import com.example.data.model.FreiGalvaoNovenaDay
import com.example.data.model.FreiGalvaoPrayer
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FreiGalvaoData {

    val novenaDays: List<FreiGalvaoNovenaDay> = listOf(
        FreiGalvaoNovenaDay(
            day = 1,
            title = "1º Dia: O Dom da Fé e o Voto de Amor a Maria",
            virtue = "Fé Viva e Devoção Mariana",
            scriptureOrThought = "«Quem confia no Senhor é como o Monte Sião: não se abala, permanece para sempre.» (Sl 124, 1)",
            meditation = "São Frei Galvão desde jovem consagrou toda a sua vida a Deus na Ordem Franciscana Alcantarina. Consagrou-se escravo de amor da Virgem Imaculada em 1766, assinando seu voto com o próprio sangue. Neste primeiro dia, colocamos sob a sua intercessão a nossa intenção mais urgente.",
            prayer = "Ó Deus de infinita bondade, que concedestes a São Frei Galvão uma fé inabalável e um terno amor à Virgem Imaculada, dai-nos a graça de crer firmemente no Vosso poder redentor. Por intercessão de Frei Galvão, atendei a súplica que Vos apresentamos com fervor. Amém.",
            isPillDay = true,
            pillInstruction = "Tome hoje a 1ª Pílula de Frei Galvão com água e reze com fé: 1 Pai Nosso, 1 Ave Maria, 1 Glória e a oração da Pílula."
        ),
        FreiGalvaoNovenaDay(
            day = 2,
            title = "2º Dia: O Amor Franciscano aos Pobres e Aflitos",
            virtue = "Caridade Fraterna e Compaixão",
            scriptureOrThought = "«Em verdade vos digo: todas as vezes que fizestes isto a um destes meus irmãos menores, a mim o fizestes.» (Mt 25, 40)",
            meditation = "Frei Galvão andava pelas ruas de São Paulo socorrendo doentes, consolando encarcerados e alimentando os desamparados. Para ele, cada irmão sofredor era a presença viva de Jesus Crucificado. Peçamos um coração misericordioso e solidário.",
            prayer = "Senhor Jesus Cristo, que inspirastes a São Frei Galvão um amor sem limites pelos necessitados e enfermos, ensinai-nos a ver em cada irmão o Vosso próprio rosto. Dignai-Vos, por seus méritos, conceder-nos a graça que Vos pedimos. Amém.",
            isPillDay = false,
            pillInstruction = "Reze a jaculatória: «Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!»"
        ),
        FreiGalvaoNovenaDay(
            day = 3,
            title = "3º Dia: O Apóstolo da Paz e da Conciliação",
            virtue = "Mansidão e Espírito Pacificador",
            scriptureOrThought = "«Bem-aventurados os que promovem a paz, porque serão chamados filhos de Deus.» (Mt 5, 9)",
            meditation = "Chamado de 'Homem da Paz e da Caridade', Frei Galvão intervinha para pacificar famílias desavindas, conciliar inimigos e restaurar a concórdia civil e eclesial. Ele levava o espírito do Seráfico Pai São Francisco de Assis por onde passava.",
            prayer = "Deus, fonte de toda concórdia e paz, que fizestes de São Frei Galvão um restaurador da harmonia nos corações e nos lares, pacificai a nossa vida e concedei a paz à nossa família e ao nosso país. Por intercessão de Frei Galvão, ouvi nossa oração. Amém.",
            isPillDay = false,
            pillInstruction = "Reze 3 Ave Marias em honra à Imaculada Conceição e a Oração Oficial."
        ),
        FreiGalvaoNovenaDay(
            day = 4,
            title = "4º Dia: A Pureza de Vida e a Castidade Imaculada",
            virtue = "Santidade e Pureza de Intenção",
            scriptureOrThought = "«Bem-aventurados os puros de coração, porque verão a Deus.» (Mt 5, 8)",
            meditation = "A pureza de Frei Galvão resplandecia em suas palavras, em seus olhos e em suas atitudes. Inspirado pela pureza da Mãe de Deus, guardou seu voto de castidade com extrema fidelidade, sendo exemplo de fortaleza espiritual contra as tentações do mundo.",
            prayer = "Espírito Santo Consolador, que adornastes a alma de São Frei Galvão com a pureza e a fidelidade aos mandamentos, purificai os nossos pensamentos, palavras e ações. Por sua intercessão pura e santa, acolhei as nossas intenções. Amém.",
            isPillDay = false,
            pillInstruction = "Medite o mistério da Visitação de Nossa Senhora e reze o Salve Rainha."
        ),
        FreiGalvaoNovenaDay(
            day = 5,
            title = "5º Dia: O Alívio dos Enfermos e o Mistério das Pílulas",
            virtue = "Esperança Viva e Consolo nas Dores",
            scriptureOrThought = "«Vinde a mim todos vós que estais cansados e sobrecarregados, e eu vos darei descanso.» (Mt 11, 28)",
            meditation = "Ao ser chamado para socorrer um jovem com dolorosas cólicas de pedras nos rins que a medicina não podia aliviar, Frei Galvão escreveu num pequeno papelzinho as palavras do Ofício da Virgem Maria, enrolou-o e deu-o ao enfermo com profunda fé. O homem foi curado imediatamente. Ali nascia a bênção que há séculos alcança milagres.",
            prayer = "Pai de Misericórdia, que revelastes Vosso poder de cura por meio da humilde devoção de Frei Galvão e das Pílulas de papel, aliviai as dores de todos os que sofrem nos leitos de hospitais e em suas casas. Concedei, se for de Vossa santa vontade, a cura de nossas enfermidades. Amém.",
            isPillDay = true,
            pillInstruction = "Tome hoje a 2ª Pílula de Frei Galvão com fé inabalável e reze o Pai Nosso e a oração pelos enfermos."
        ),
        FreiGalvaoNovenaDay(
            day = 6,
            title = "6º Dia: O Protetor das Mães e das Parturientes",
            virtue = "Defesa e Bênção da Vida",
            scriptureOrThought = "«A mulher, quando está para dar à luz, sente tristeza... mas, depois de ter dado à luz o menino, já não se lembra da aflição, pelo júbilo de haver nascido um homem no mundo.» (Jo 16, 21)",
            meditation = "Outro milagre seminal de Frei Galvão foi atender a um marido desesperado cuja esposa estava em trabalho de parto de altíssimo risco, sem forças e desenganada pelos médicos. Com as pílulas e a oração 'Post partum...', a mãe deu à luz uma criança vigorosa e recuperou prontamente a saúde. Até hoje, milhares de mães recorrem à sua proteção.",
            prayer = "Senhor Deus da Vida, que consagrastes a maternidade no seio da Virgem Maria, abençoai todas as gestantes, as mães que anseiam por um filho e as que enfrentam gestações difíceis. Pela intercessão de São Frei Galvão, concedei um parto abençoado e a saúde de mães e bebês. Amém.",
            isPillDay = false,
            pillInstruction = "Reze a Oração pelas Mães e Gestantes pedindo a proteção de todas as famílias."
        ),
        FreiGalvaoNovenaDay(
            day = 7,
            title = "7º Dia: O Edificador do Mosteiro da Luz e o Homem de Oração",
            virtue = "Trabalho Dedicado e Contemplação",
            scriptureOrThought = "«Se o Senhor não edificar a casa, em vão trabalham os que a constroem.» (Sl 127, 1)",
            meditation = "Durante décadas, Frei Galvão projetou, angariou recursos e ajudou a carregar as pedras para a construção do Convento da Luz em São Paulo, hoje Mosteiro das Irmãs Concepcionistas e Patrimônio Cultural da Humanidade. Unia trabalho árduo com oração contínua diante do Sacrário.",
            prayer = "Deus Onipotente, que destes a São Frei Galvão sabedoria arquitetônica e força apostólica para erigir o Mosteiro da Luz como fortaleza de oração perpétua, iluminai nossas decisões e dai-nos perseverança em nossas tarefas diárias. Concedei-nos a graça que hoje confiamos a suas mãos. Amém.",
            isPillDay = false,
            pillInstruction = "Reze a Oração do Mosteiro da Luz e agradeça pelas bênçãos recebidas."
        ),
        FreiGalvaoNovenaDay(
            day = 8,
            title = "8º Dia: Os Dons Místicos e a Bilocação Milagrosa",
            virtue = "Docilidade ao Espírito Santo",
            scriptureOrThought = "«A cada um é dada a manifestação do Espírito para proveito comum.» (1 Cor 12, 7)",
            meditation = "Deus cumulou Frei Galvão de carismas extraordinários, como o dom da ciência, profecia e a prodigiosa bilocação. Em 1810, enquanto pregava no púlpito da igreja em São Paulo, parou por instantes e foi visto ao mesmo tempo às margens do Rio Tietê, salvando um barqueiro e um menino de se afogarem nas águas revoltas.",
            prayer = "Senhor Deus, admirável em Vossos santos, que manifestastes Vossos prodígios por meio de Frei Galvão para resgatar vidas em perigo extremo, socorrei-nos em todas as nossas aflições materiais e espirituais. Nada é impossível para o Vosso poder. Amém.",
            isPillDay = false,
            pillInstruction = "Medite o auxílio sobrenatural de Deus e renove a sua confiança filial."
        ),
        FreiGalvaoNovenaDay(
            day = 9,
            title = "9º Dia: O Trânsito Seráfico e a Glória da Canonização",
            virtue = "Perseverança Final e Comunhão dos Santos",
            scriptureOrThought = "«Combati o bom combate, completei a corrida, guardei a fé. Desde agora, está reservada para mim a coroa da justiça.» (2 Tm 4, 7-8)",
            meditation = "No dia 23 de dezembro de 1822, com 83 anos de idade e fama inabalável de santidade, Frei Galvão adormeceu no Senhor no Mosteiro da Luz. Em 11 de maio de 2007, o Papa Bento XVI o proclamou solenemente o primeiro Santo genuinamente brasileiro, canonizado em solo pátrio. Concluímos nossa novena certos de sua poderosa intercessão.",
            prayer = "Ó Deus, Pai de misericórdia, que fizestes de São Frei Galvão o primeiro Santo nascido no Brasil e Vosso servo incansável, nós Vos agradecemos pelas inúmeras bênçãos concedidas por suas mãos. Concedei-nos a graça desta novena e a firmeza de caminhar nos caminhos do Evangelho até o Céu. Amém.",
            isPillDay = true,
            pillInstruction = "Tome hoje a 3ª Pílula de Frei Galvão com profunda gratidão e reze 1 Pai Nosso, 1 Ave Maria, 1 Glória e a Oração Oficial."
        )
    )

    val prayers: List<FreiGalvaoPrayer> = listOf(
        FreiGalvaoPrayer(
            id = "oracao_oficial",
            title = "Oração Oficial a São Frei Galvão",
            category = "Oficial",
            intentionSubtitle = "Oração de Intercessão Geral e Pedido de Graças",
            portugueseText = "Deus, Pai de misericórdia, que fizestes de São Frei Galvão um instrumento de caridade e de paz para o Vosso povo, nós Vos agradecemos por nos terdes dado este Santo como nosso intercessor.\n\nConcedei-nos, por seus méritos, a graça da fé viva, da pureza de vida e do amor incansável aos irmãos, especialmente aos mais pobres e sofredores.\n\nPor intercessão de São Frei Galvão e de Maria Imaculada, atendei a súplica que com inteira confiança agora Vos fazemos: (faça o pedido da graça desejada).\n\nPor Cristo, nosso Senhor. Amém.\n\n(Reza-se 1 Pai Nosso, 1 Ave Maria e 1 Glória ao Pai)",
            latinAntiphon = "Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis.",
            historicalContext = "Aprovada canonicamente pela Arquidiocese de São Paulo e postulada na solenidade de sua canonização pelo Papa Bento XVI.",
            isFavorite = true
        ),
        FreiGalvaoPrayer(
            id = "oracao_pilulas",
            title = "Oração das Pílulas de Frei Galvão",
            category = "Pílulas Milagrosas",
            intentionSubtitle = "Para ser rezada ao tomar cada uma das 3 Pílulas",
            portugueseText = "Santíssima Trindade, Pai, Filho e Espírito Santo, eu Vos adoro, louvo e dou graças pelos prodígios que operastes na vida e no ministério de São Frei Galvão.\n\nAo tomar com fé esta bendita Pílula, recordo a santa oração que Frei Galvão escreveu de próprio punho em louvor à Mãe de Deus:\n\n«Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!»\n\n(Em Latim: Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis!)\n\nPor este infinito mistério da Maternidade Divina e da Perpétua Virgindade de Maria, curai o meu corpo, pacificai a minha alma e concedei-me a graça que ardentemente imploro. São Frei Galvão, rogai por nós!",
            latinAntiphon = "Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis.",
            historicalContext = "As palavras escritas por Frei Galvão no papel são uma antífona litúrgica milenar do Ofício Parvo de Nossa Senhora.",
            isFavorite = true
        ),
        FreiGalvaoPrayer(
            id = "oracao_gestantes",
            title = "Oração pelas Gestantes e Mães",
            category = "Gestantes e Mães",
            intentionSubtitle = "Proteção na gravidez, parto abençoado e saúde do bebê",
            portugueseText = "Ó glorioso São Frei Galvão, que em vida socorrestes tantas mães aflitas e livrastes de perigo iminente as parturientes desenganadas pelos médicos, volvei vosso olhar paterno para mim e para o filho que carrego em meu seio (ou pelo filho de quem vos recomendo).\n\nAbençoai cada dia desta gestação. Afastai todo receio, complicação ou dor excessiva. Dai-me a alegria de um parto sereno, seguro e abençoado, trazendo à luz uma criatura sã para a maior glória de Deus.\n\nAssim como Maria concebeu Jesus pelo poder do Espírito Santo, protegei minha família com o manto da graça divina. São Frei Galvão, amigo dos nascituros e consolador das mães, rogai por nós!",
            latinAntiphon = "Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis.",
            historicalContext = "Inspirada no milagre de Dona Ana Maria de Oliveira em 1785, que deu à luz com plena saúde após as orações de Frei Galvão.",
            isFavorite = false
        ),
        FreiGalvaoPrayer(
            id = "oracao_enfermos",
            title = "Oração pelos Doentes e Casos Renais",
            category = "Enfermos e Saúde",
            intentionSubtitle = "Cura de pedras nos rins, cólicas, inflamações e dores agudas",
            portugueseText = "Senhor Jesus, Médico dos corpos e das almas, que pelos caminhos da Galileia curastes os coxos, purificastes os leprosos e abristes os olhos dos cegos, olhai com compaixão para os Vossos filhos que sofrem com cruéis enfermidades, pedras nos rins, cólicas nefréticas e dores sem remédio humano.\n\nPela intercessão amorosa de Vosso servo São Frei Galvão, que tantas vezes devolveu a saúde aos desenganados, lançai sobre nós a Vossa mão restauradora. Se for para o bem da nossa salvação, dai-nos a cura completa; e dai-nos sempre paciência para suportar a cruz em união com a Vossa Paixão. São Frei Galvão, socorro dos enfermos, rogai por nós!",
            latinAntiphon = "Sancte Antoni de Sancta Anna Galvão, ora pro nobis.",
            historicalContext = "Origem da primeira pílula: Frei Galvão curou um jovem com cólicas violentas causadas por cálculos renais.",
            isFavorite = false
        ),
        FreiGalvaoPrayer(
            id = "voto_sangue",
            title = "Consagração Mariana do Voto de Sangue (1766)",
            category = "Consagração",
            intentionSubtitle = "Consagração heróica de Frei Galvão a Nossa Senhora da Conceição",
            portugueseText = "«Eu, Frei Antônio de Sant'Ana Galvão, menor descalço da mais estreita observância de Nosso Seráfico Pai São Francisco, prometo e juro com o meu próprio sangue defender até à morte que a Virgem Maria, Mãe de Deus, foi concebida sem pecado original, desde o primeiro instante de sua animação.\n\nE tomo-a hoje por minha Senhora, Mãe, Advogada e Mestra soberana de todas as minhas ações, entregando-lhe para sempre minha alma, meu corpo e minha vida.»\n\n(Reza-se 3 Glórias ao Pai em louvor à Imaculada Conceição)",
            latinAntiphon = "Tota pulchra es, Maria, et macula originalis non est in te.",
            historicalContext = "Escrito e assinado com sangue por Frei Galvão em 9 de março de 1766, quase um século antes da proclamação do dogma da Imaculada.",
            isFavorite = false
        ),
        FreiGalvaoPrayer(
            id = "ladainha_frei_galvao",
            title = "Ladainha de São Frei Galvão",
            category = "Ladainha",
            intentionSubtitle = "Súplica litânica das virtudes de Frei Galvão",
            portugueseText = "Senhor, tende piedade de nós.\nCristo, tende piedade de nós.\nSenhor, tende piedade de nós.\nCristo, ouvi-nos.\nCristo, atendei-nos.\n\nDeus Pai dos Céus, tende piedade de nós.\nDeus Filho, Redentor do mundo, tende piedade de nós.\nDeus Espírito Santo, tende piedade de nós.\nSantíssima Trindade, que sois um só Deus, tende piedade de nós.\n\nSanta Maria, Rainha dos Anjos e dos Santos, rogai por nós.\nSão Frei Galvão, vaso de eleição de Deus, rogai por nós.\nSão Frei Galvão, primeiro santo do Brasil, rogai por nós.\nSão Frei Galvão, lírio de pureza e castidade, rogai por nós.\nSão Frei Galvão, verdadeiro filho do Seráfico Pai Francisco, rogai por nós.\nSão Frei Galvão, arauto do Evangelho da Paz, rogai por nós.\nSão Frei Galvão, consolador dos encarcerados e aflitos, rogai por nós.\nSão Frei Galvão, alívio dos doentes e parturientes, rogai por nós.\nSão Frei Galvão, construtor abnegado do Mosteiro da Luz, rogai por nós.\nSão Frei Galvão, místico abrasado no amor ao Santíssimo Sacramento, rogai por nós.\nSão Frei Galvão, escravo fidelíssimo da Virgem Imaculada, rogai por nós.\n\nCordeiro de Deus, que tirais o pecado do mundo, perdoai-nos, Senhor.\nCordeiro de Deus, que tirais o pecado do mundo, ouvi-nos, Senhor.\nCordeiro de Deus, que tirais o pecado do mundo, tende piedade de nós.\n\nRogai por nós, São Frei Galvão,\nPara que sejamos dignos das promessas de Cristo. Amém.",
            latinAntiphon = "Ora pro nobis, Beate Antoni de Galvao, ut digni efficiamur promissionibus Christi.",
            historicalContext = "Devoção litânica rezada tradicionalmente no Santuário de Frei Galvão e no Mosteiro da Luz.",
            isFavorite = false
        )
    )

    val miracles: List<FreiGalvaoMiracle> = listOf(
        FreiGalvaoMiracle(
            id = "milagre_pilulas",
            title = "A Origem das Famosas Pílulas de Papel",
            subtitle = "A cura da cólica nefrética e o poder da fé",
            yearAndPlace = "São Paulo, Convento de São Francisco (século XVIII)",
            summary = "Um jovem agonizava com dores lancinantes provocadas por pedras nos rins. Frei Galvão escreveu as palavras do Ofício da Virgem Maria em pequenos papéis, deu-os como pílulas e o jovem expeliu o cálculo instantaneamente.",
            fullStory = "Certo dia, um jovem aflito bateu à porta do Convento de São Francisco em São Paulo. Ele padecia de violentíssimas dores causadas por cálculos na bexiga e rins, e a medicina da época nada mais podia fazer para estancar a agonia. Vendo o jovem prostrado em lágrimas, Frei Galvão sentiu profunda compaixão inspirada pelo Céu.\n\nLembrou-se imediatamente do mistério glorioso da Maternidade de Maria. Tomou um pequeno pedaço de papel fino de pergaminho e escreveu com tinta de pena as veneráveis palavras da liturgia:\n\n«Post partum, Virgo, inviolata permansisti: Dei Genitrix, intercede pro nobis»\n(Depois do parto, ó Virgem, permaneceste intacta: Mãe de Deus, intercedei por nós!)\n\nEnrolou o bilhetinho em formato de pequena pílula, mandou que o rapaz o engolisse com um gole de água enquanto rezava com fervor à Virgem Santíssima. Em poucos instantes, cessaram as cólicas e o rapaz expeliu as pedras sem dor alguma, perfeitamente restabelecido. A notícia espalhou-se como fogo santo por toda a província paulista e o Brasil.",
            canonicalSignificance = "Este prodígio originou a maior devoção popular do Santo, mantida até hoje ininterruptamente pelas Irmãs Concepcionistas do Mosteiro da Luz."
        ),
        FreiGalvaoMiracle(
            id = "milagre_parto",
            title = "O Parto de Risco de Dona Ana Maria",
            subtitle = "A salvação milagrosa de mãe e filho",
            yearAndPlace = "Vale do Paraíba / São Paulo (1785)",
            summary = "Um homem a cavalo cavalgou desesperado em busca de Frei Galvão porque sua esposa e filho estavam à beira da morte em trabalho de parto. Frei Galvão enviou três pílulas de papel e o parto realizou-se com perfeita saúde.",
            fullStory = "Em outra célebre ocasião, um senhor desesperado procurou Frei Galvão, informando que sua esposa estava em trabalho de parto gravíssimo, prestes a expirar juntamente com a criança que não conseguia nascer, segundo o veredito unânime dos médicos e parteiras.\n\nFrei Galvão, impedido de se deslocar imediatamente até a fazenda distante, preparou três pílulas de papel com a mesma antífona mariana «Post partum, Virgo, inviolata permansisti...». Entregou-as ao cavaleiro dizendo: 'Ide em paz, dai-lhe com água e confiai na Virgem Santíssima'.\n\nAo engolir a primeira pílula, a mulher sentiu serenar suas dores; ao tomar as seguintes, deu à luz um menino forte e saudável, sem a menor complicação cirúrgica ou hemorragia. Desde então, São Frei Galvão tornou-se o especial padroeiro e refúgio celestial de todas as mães, gestantes e recém-nascidos.",
            canonicalSignificance = "O milagre reconhecido oficialmente pela Congregação para as Causas dos Santos para sua canonização também envolveu um parto de altíssimo risco e cura inexplicável."
        ),
        FreiGalvaoMiracle(
            id = "milagre_bilocacao",
            title = "A Prodigiosa Bilocação no Rio Tietê",
            subtitle = "Estar em dois lugares ao mesmo tempo para salvar uma vida",
            yearAndPlace = "São Paulo, Púlpito do Convento e Rio Tietê (1810)",
            summary = "Enquanto pregava com voz solene no púlpito da igreja repleta de fiéis, Frei Galvão fez uma pausa imóvel. Nesse mesmo instante, a quilômetros dali, foi visto salvando um barqueiro e um menino de se afogarem nas águas revoltas do Rio Tietê.",
            fullStory = "O fenômeno místico da bilocação (a capacidade concedida por Deus a santos de estar simultaneamente em dois lugares físicos distintos) foi testemunhado juramentadamente no processo de beatificação de Frei Galvão.\n\nEm 1810, durante um sermão na igreja do Convento de São Francisco, Frei Galvão de repente interrompeu sua pregação, apoiou a cabeça nas mãos e permaneceu em silêncio absoluto e oração imóvel por cerca de dez a quinze minutos. O povo esperava em silêncio respeitoso. Ao retomar a voz, disse suavemente: 'Rezem meus irmãos, demos graças a Deus'.\n\nMais tarde, chegou à cidade o barqueiro Manuel, que narrou aterrorizado e comovido que, naquele exato momento do dia, sua canoa naufragara nas águas violentas e traiçoeiras do Rio Tietê. Ele e seu ajudante estavam prestes a ser tragados pela correnteza mortal quando o venerável Frei Galvão apareceu visivelmente sobre a margem, estendeu-lhes a mão com força sobrenatural, puxou-os para terra firme e desapareceu no ar diante de seus olhos pasmos.",
            canonicalSignificance = "Documentado sob juramento canônico por múltiplas testemunhas oculares e registrado na história mística do Brasil."
        ),
        FreiGalvaoMiracle(
            id = "milagre_mosteiro",
            title = "A Construção do Mosteiro da Luz",
            subtitle = "Pedra por pedra pelas mãos do Santo Frade",
            yearAndPlace = "São Paulo, Avenida Tiradentes (1774-1822)",
            summary = "Fundador espiritual e arquiteto prático, Frei Galvão passou quase 50 anos construindo com as próprias mãos o Convento das Concepcionistas da Luz, declarado Patrimônio Mundial pela UNESCO.",
            fullStory = "Guiado por visões místicas e pelo apelo de Santa Madre Paulina do Menino Jesus (Madre Helena Maria do Espírito Santo), Frei Galvão dedicou o restante de sua vida terrena a erguer o Mosteiro da Imaculada Conceição da Luz em São Paulo.\n\nEle não apenas desenhou as plantas de taipa de pilão e a arquitetura colonial singular, mas trabalhava como operário comum: carregava pedras, amassava barro, talhava madeiras e pedia esmolas de porta em porta para pagar os artífices. Quando faltavam mantimentos para os operários, multiplicavam-se miraculosamente os pães e a farinha.\n\nHoje, o Mosteiro da Luz é um centro de peregrinação nacional, onde até hoje suas herdeiras espirituais, as Irmãs Concepcionistas enclausuradas, confeccionam artesanalmente em oração perpétua as Pílulas de São Frei Galvão para distribuir gratuitamente a milhares de fiéis do mundo inteiro.",
            canonicalSignificance = "Túmulo de São Frei Galvão e coração vivo da espiritualidade seráfica e mariana do Brasil."
        )
    )

    private const val PREFS_NAME = "frei_galvao_prefs"
    private const val KEY_NOVENA_DAYS = "novena_completed_days"
    private const val KEY_INTENTIONS_JSON = "user_intentions_json"

    fun getCompletedNovenaDays(context: Context): Set<Int> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(KEY_NOVENA_DAYS, emptySet()) ?: emptySet()
        return set.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun toggleNovenaDayCompleted(context: Context, day: Int): Set<Int> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val current = getCompletedNovenaDays(context).toMutableSet()
        if (current.contains(day)) {
            current.remove(day)
        } else {
            current.add(day)
        }
        prefs.edit().putStringSet(KEY_NOVENA_DAYS, current.map { it.toString() }.toSet()).apply()
        return current
    }

    fun resetNovena(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_NOVENA_DAYS).apply()
    }

    fun loadIntentions(context: Context): List<FreiGalvaoIntention> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonStr = prefs.getString(KEY_INTENTIONS_JSON, null)
        if (jsonStr.isNullOrEmpty()) {
            return getDefaultIntentions()
        }
        return try {
            val list = mutableListOf<FreiGalvaoIntention>()
            val array = JSONArray(jsonStr)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                list.add(
                    FreiGalvaoIntention(
                        id = obj.getString("id"),
                        devoteeName = obj.optString("devoteeName", "Devoto"),
                        intentionType = obj.optString("intentionType", "Causas Difíceis"),
                        description = obj.optString("description", ""),
                        dateCreated = obj.optString("dateCreated", "Hoje"),
                        prayersCount = obj.optInt("prayersCount", 1),
                        isGraceReceived = obj.optBoolean("isGraceReceived", false)
                    )
                )
            }
            list
        } catch (e: Exception) {
            getDefaultIntentions()
        }
    }

    fun saveIntentions(context: Context, intentions: List<FreiGalvaoIntention>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val array = JSONArray()
        for (item in intentions) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("devoteeName", item.devoteeName)
                put("intentionType", item.intentionType)
                put("description", item.description)
                put("dateCreated", item.dateCreated)
                put("prayersCount", item.prayersCount)
                put("isGraceReceived", item.isGraceReceived)
            }
            array.put(obj)
        }
        prefs.edit().putString(KEY_INTENTIONS_JSON, array.toString()).apply()
    }

    fun addIntention(context: Context, name: String, type: String, description: String): List<FreiGalvaoIntention> {
        val current = loadIntentions(context).toMutableList()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val newItem = FreiGalvaoIntention(
            id = "intent_${System.currentTimeMillis()}",
            devoteeName = name.ifBlank { "Devoto de Frei Galvão" },
            intentionType = type,
            description = description,
            dateCreated = dateFormat.format(Date()),
            prayersCount = 1,
            isGraceReceived = false
        )
        current.add(0, newItem)
        saveIntentions(context, current)
        return current
    }

    fun incrementPrayerCount(context: Context, id: String): List<FreiGalvaoIntention> {
        val current = loadIntentions(context).map {
            if (it.id == id) it.copy(prayersCount = it.prayersCount + 1) else it
        }
        saveIntentions(context, current)
        return current
    }

    fun toggleGraceReceived(context: Context, id: String): List<FreiGalvaoIntention> {
        val current = loadIntentions(context).map {
            if (it.id == id) it.copy(isGraceReceived = !it.isGraceReceived) else it
        }
        saveIntentions(context, current)
        return current
    }

    fun deleteIntention(context: Context, id: String): List<FreiGalvaoIntention> {
        val current = loadIntentions(context).filterNot { it.id == id }
        saveIntentions(context, current)
        return current
    }

    private fun getDefaultIntentions(): List<FreiGalvaoIntention> = listOf(
        FreiGalvaoIntention(
            id = "default_1",
            devoteeName = "Família Silva",
            intentionType = "Gestação e Parto",
            description = "Pela saúde da minha esposa grávida de 7 meses e para que o parto ocorra em paz, sob a intercessão de São Frei Galvão.",
            dateCreated = "11/09/2026",
            prayersCount = 9,
            isGraceReceived = false
        ),
        FreiGalvaoIntention(
            id = "default_2",
            devoteeName = "Antônio Carlos",
            intentionType = "Cálculos Renais",
            description = "Pela cura das dores e expelimento de pedras nos rins sem necessidade de cirurgia, tomando a pílula de Frei Galvão com fé.",
            dateCreated = "10/09/2026",
            prayersCount = 5,
            isGraceReceived = true
        ),
        FreiGalvaoIntention(
            id = "default_3",
            devoteeName = "Maria de Lourdes",
            intentionType = "Família e Paz",
            description = "Pela conversão e reconciliação dos meus filhos e paz em nosso lar.",
            dateCreated = "08/09/2026",
            prayersCount = 14,
            isGraceReceived = false
        )
    )
}
