CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE public.admins (
    user_id bigint NOT NULL
);


CREATE TABLE public.classrooms (
    classroom_id bigint NOT NULL,
    description character varying(255),
    number character varying(255)
);


CREATE SEQUENCE public.classrooms_classroom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.classrooms_courses (
    classroom_id bigint NOT NULL,
    course_id bigint NOT NULL
);


CREATE TABLE public.courses (
    course_id bigint NOT NULL,
    course_description character varying(255),
    course_name character varying(255)
);

CREATE SEQUENCE public.courses_course_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.groups (
    group_id bigint NOT NULL,
    group_name character varying(255)
);



CREATE TABLE public.groups_courses (
    group_id bigint NOT NULL,
    course_id bigint NOT NULL
);


CREATE SEQUENCE public.groups_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.lessons (
    lesson_id bigint NOT NULL,
    day smallint,
    lesson_number smallint,
    classroom_id bigint,
    course_id bigint,
    group_id bigint,
    teacher_id bigint,
    CONSTRAINT lessons_day_check CHECK (((day >= 0) AND (day <= 4))),
    CONSTRAINT lessons_lesson_number_check CHECK (((lesson_number >= 0) AND (lesson_number <= 3)))
);

CREATE SEQUENCE public.lessons_lesson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.students (
    user_id bigint NOT NULL,
    group_id bigint
);


CREATE TABLE public.teachers (
    user_id bigint NOT NULL,
    teach_course_id bigint
);

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    role smallint,
    username character varying(255),
    CONSTRAINT users_role_check CHECK (((role >= 0) AND (role <= 2)))
);

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
ALTER TABLE ONLY public.classrooms ALTER COLUMN classroom_id SET DEFAULT nextval('public.classrooms_classroom_id_seq'::regclass);
--
--
ALTER TABLE ONLY public.courses ALTER COLUMN course_id SET DEFAULT nextval('public.courses_course_id_seq'::regclass);


ALTER TABLE ONLY public.groups ALTER COLUMN group_id SET DEFAULT nextval('public.groups_group_id_seq'::regclass);


ALTER TABLE ONLY public.lessons ALTER COLUMN lesson_id SET DEFAULT nextval('public.lessons_lesson_id_seq'::regclass);


ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);



ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (user_id);


ALTER TABLE ONLY public.classrooms
    ADD CONSTRAINT classrooms_pkey PRIMARY KEY (classroom_id);


ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);


ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (group_id);



ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (lesson_id);


ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (user_id);


ALTER TABLE ONLY public.teachers
    ADD CONSTRAINT teachers_pkey PRIMARY KEY (user_id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);

-- ALTER TABLE ONLY public.lessons
--     ADD CONSTRAINT fk17ucc7gjfjddsyi0gvstkqeat FOREIGN KEY (course_id) REFERENCES public.courses(course_id);

-- ALTER TABLE ONLY public.groups_courses
--     ADD CONSTRAINT fk68xnkbo7urhqormcaa50dcxuw FOREIGN KEY (course_id) REFERENCES public.courses(course_id);
--
-- ALTER TABLE ONLY public.groups_courses
--     ADD CONSTRAINT fkb5nvrwxye8n0ct77q8s3war1x FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
--
-- ALTER TABLE ONLY public.teachers
--     ADD CONSTRAINT fkb8dct7w2j1vl1r2bpstw5isc0 FOREIGN KEY (user_id) REFERENCES public.users(user_id);
--
--
-- ALTER TABLE ONLY public.lessons
--     ADD CONSTRAINT fkbffxqtymudjwdb39m7dnjn4ey FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);
--
-- ALTER TABLE ONLY public.lessons
--     ADD CONSTRAINT fkbr76cuebuufbbltujpfq04tto FOREIGN KEY (teacher_id) REFERENCES public.teachers(user_id);
--
-- ALTER TABLE ONLY public.students
--     ADD CONSTRAINT fkdt1cjx5ve5bdabmuuf3ibrwaq FOREIGN KEY (user_id) REFERENCES public.users(user_id);
--
-- ALTER TABLE ONLY public.admins
--     ADD CONSTRAINT fkgc8dtql9mkq268detxiox7fpm FOREIGN KEY (user_id) REFERENCES public.users(user_id);
--
-- ALTER TABLE ONLY public.teachers
--     ADD CONSTRAINT fkk805y9f18ppaml2ng1c1fhe7n FOREIGN KEY (teach_course_id) REFERENCES public.courses(course_id);
--
-- ALTER TABLE ONLY public.classrooms_courses
--     ADD CONSTRAINT fkk8rbwp31oatef2ohykhr6b6hq FOREIGN KEY (classroom_id) REFERENCES public.classrooms(classroom_id);
--
-- ALTER TABLE ONLY public.students
--     ADD CONSTRAINT fkmsev1nou0j86spuk5jrv19mss FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
--
-- ALTER TABLE ONLY public.classrooms_courses
--     ADD CONSTRAINT fkr33hf7ya3mj3l45uk13st6ufk FOREIGN KEY (course_id) REFERENCES public.courses(course_id);
--
-- ALTER TABLE ONLY public.lessons
--     ADD CONSTRAINT fktdolsaotaqlwxbxwaxt00kimk FOREIGN KEY (group_id) REFERENCES public.groups(group_id);
--
