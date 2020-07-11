--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin_event_entity; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.admin_event_entity (
    id character varying(36) NOT NULL,
    admin_event_time bigint,
    realm_id character varying(255),
    operation_type character varying(255),
    auth_realm_id character varying(255),
    auth_client_id character varying(255),
    auth_user_id character varying(255),
    ip_address character varying(255),
    resource_path character varying(2550),
    representation text,
    error character varying(255),
    resource_type character varying(64)
);


ALTER TABLE public.admin_event_entity OWNER TO keycloak;

--
-- Name: associated_policy; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.associated_policy (
    policy_id character varying(36) NOT NULL,
    associated_policy_id character varying(36) NOT NULL
);


ALTER TABLE public.associated_policy OWNER TO keycloak;

--
-- Name: authentication_execution; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.authentication_execution (
    id character varying(36) NOT NULL,
    alias character varying(255),
    authenticator character varying(36),
    realm_id character varying(36),
    flow_id character varying(36),
    requirement integer,
    priority integer,
    authenticator_flow boolean DEFAULT false NOT NULL,
    auth_flow_id character varying(36),
    auth_config character varying(36)
);


ALTER TABLE public.authentication_execution OWNER TO keycloak;

--
-- Name: authentication_flow; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.authentication_flow (
    id character varying(36) NOT NULL,
    alias character varying(255),
    description character varying(255),
    realm_id character varying(36),
    provider_id character varying(36) DEFAULT 'basic-flow'::character varying NOT NULL,
    top_level boolean DEFAULT false NOT NULL,
    built_in boolean DEFAULT false NOT NULL
);


ALTER TABLE public.authentication_flow OWNER TO keycloak;

--
-- Name: authenticator_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.authenticator_config (
    id character varying(36) NOT NULL,
    alias character varying(255),
    realm_id character varying(36)
);


ALTER TABLE public.authenticator_config OWNER TO keycloak;

--
-- Name: authenticator_config_entry; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.authenticator_config_entry (
    authenticator_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.authenticator_config_entry OWNER TO keycloak;

--
-- Name: broker_link; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.broker_link (
    identity_provider character varying(255) NOT NULL,
    storage_provider_id character varying(255),
    realm_id character varying(36) NOT NULL,
    broker_user_id character varying(255),
    broker_username character varying(255),
    token text,
    user_id character varying(255) NOT NULL
);


ALTER TABLE public.broker_link OWNER TO keycloak;

--
-- Name: client; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client (
    id character varying(36) NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    full_scope_allowed boolean DEFAULT false NOT NULL,
    client_id character varying(255),
    not_before integer,
    public_client boolean DEFAULT false NOT NULL,
    secret character varying(255),
    base_url character varying(255),
    bearer_only boolean DEFAULT false NOT NULL,
    management_url character varying(255),
    surrogate_auth_required boolean DEFAULT false NOT NULL,
    realm_id character varying(36),
    protocol character varying(255),
    node_rereg_timeout integer DEFAULT 0,
    frontchannel_logout boolean DEFAULT false NOT NULL,
    consent_required boolean DEFAULT false NOT NULL,
    name character varying(255),
    service_accounts_enabled boolean DEFAULT false NOT NULL,
    client_authenticator_type character varying(255),
    root_url character varying(255),
    description character varying(255),
    registration_token character varying(255),
    standard_flow_enabled boolean DEFAULT true NOT NULL,
    implicit_flow_enabled boolean DEFAULT false NOT NULL,
    direct_access_grants_enabled boolean DEFAULT false NOT NULL,
    always_display_in_console boolean DEFAULT false NOT NULL
);


ALTER TABLE public.client OWNER TO keycloak;

--
-- Name: client_attributes; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_attributes (
    client_id character varying(36) NOT NULL,
    value character varying(4000),
    name character varying(255) NOT NULL
);


ALTER TABLE public.client_attributes OWNER TO keycloak;

--
-- Name: client_auth_flow_bindings; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_auth_flow_bindings (
    client_id character varying(36) NOT NULL,
    flow_id character varying(36),
    binding_name character varying(255) NOT NULL
);


ALTER TABLE public.client_auth_flow_bindings OWNER TO keycloak;

--
-- Name: client_default_roles; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_default_roles (
    client_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.client_default_roles OWNER TO keycloak;

--
-- Name: client_initial_access; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_initial_access (
    id character varying(36) NOT NULL,
    realm_id character varying(36) NOT NULL,
    "timestamp" integer,
    expiration integer,
    count integer,
    remaining_count integer
);


ALTER TABLE public.client_initial_access OWNER TO keycloak;

--
-- Name: client_node_registrations; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_node_registrations (
    client_id character varying(36) NOT NULL,
    value integer,
    name character varying(255) NOT NULL
);


ALTER TABLE public.client_node_registrations OWNER TO keycloak;

--
-- Name: client_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_scope (
    id character varying(36) NOT NULL,
    name character varying(255),
    realm_id character varying(36),
    description character varying(255),
    protocol character varying(255)
);


ALTER TABLE public.client_scope OWNER TO keycloak;

--
-- Name: client_scope_attributes; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_scope_attributes (
    scope_id character varying(36) NOT NULL,
    value character varying(2048),
    name character varying(255) NOT NULL
);


ALTER TABLE public.client_scope_attributes OWNER TO keycloak;

--
-- Name: client_scope_client; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_scope_client (
    client_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL,
    default_scope boolean DEFAULT false NOT NULL
);


ALTER TABLE public.client_scope_client OWNER TO keycloak;

--
-- Name: client_scope_role_mapping; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_scope_role_mapping (
    scope_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.client_scope_role_mapping OWNER TO keycloak;

--
-- Name: client_session; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_session (
    id character varying(36) NOT NULL,
    client_id character varying(36),
    redirect_uri character varying(255),
    state character varying(255),
    "timestamp" integer,
    session_id character varying(36),
    auth_method character varying(255),
    realm_id character varying(255),
    auth_user_id character varying(36),
    current_action character varying(36)
);


ALTER TABLE public.client_session OWNER TO keycloak;

--
-- Name: client_session_auth_status; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_session_auth_status (
    authenticator character varying(36) NOT NULL,
    status integer,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_auth_status OWNER TO keycloak;

--
-- Name: client_session_note; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_session_note (
    name character varying(255) NOT NULL,
    value character varying(255),
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_note OWNER TO keycloak;

--
-- Name: client_session_prot_mapper; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_session_prot_mapper (
    protocol_mapper_id character varying(36) NOT NULL,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_prot_mapper OWNER TO keycloak;

--
-- Name: client_session_role; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_session_role (
    role_id character varying(255) NOT NULL,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_role OWNER TO keycloak;

--
-- Name: client_user_session_note; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.client_user_session_note (
    name character varying(255) NOT NULL,
    value character varying(2048),
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_user_session_note OWNER TO keycloak;

--
-- Name: component; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.component (
    id character varying(36) NOT NULL,
    name character varying(255),
    parent_id character varying(36),
    provider_id character varying(36),
    provider_type character varying(255),
    realm_id character varying(36),
    sub_type character varying(255)
);


ALTER TABLE public.component OWNER TO keycloak;

--
-- Name: component_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.component_config (
    id character varying(36) NOT NULL,
    component_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(4000)
);


ALTER TABLE public.component_config OWNER TO keycloak;

--
-- Name: composite_role; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.composite_role (
    composite character varying(36) NOT NULL,
    child_role character varying(36) NOT NULL
);


ALTER TABLE public.composite_role OWNER TO keycloak;

--
-- Name: credential; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.credential (
    id character varying(36) NOT NULL,
    salt bytea,
    type character varying(255),
    user_id character varying(36),
    created_date bigint,
    user_label character varying(255),
    secret_data text,
    credential_data text,
    priority integer
);


ALTER TABLE public.credential OWNER TO keycloak;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO keycloak;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO keycloak;

--
-- Name: default_client_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.default_client_scope (
    realm_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL,
    default_scope boolean DEFAULT false NOT NULL
);


ALTER TABLE public.default_client_scope OWNER TO keycloak;

--
-- Name: event_entity; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.event_entity (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    details_json character varying(2550),
    error character varying(255),
    ip_address character varying(255),
    realm_id character varying(255),
    session_id character varying(255),
    event_time bigint,
    type character varying(255),
    user_id character varying(255)
);


ALTER TABLE public.event_entity OWNER TO keycloak;

--
-- Name: fed_user_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_attribute (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    value character varying(2024)
);


ALTER TABLE public.fed_user_attribute OWNER TO keycloak;

--
-- Name: fed_user_consent; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_consent (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    created_date bigint,
    last_updated_date bigint,
    client_storage_provider character varying(36),
    external_client_id character varying(255)
);


ALTER TABLE public.fed_user_consent OWNER TO keycloak;

--
-- Name: fed_user_consent_cl_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_consent_cl_scope (
    user_consent_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.fed_user_consent_cl_scope OWNER TO keycloak;

--
-- Name: fed_user_credential; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_credential (
    id character varying(36) NOT NULL,
    salt bytea,
    type character varying(255),
    created_date bigint,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    user_label character varying(255),
    secret_data text,
    credential_data text,
    priority integer
);


ALTER TABLE public.fed_user_credential OWNER TO keycloak;

--
-- Name: fed_user_group_membership; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_group_membership (
    group_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_group_membership OWNER TO keycloak;

--
-- Name: fed_user_required_action; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_required_action (
    required_action character varying(255) DEFAULT ' '::character varying NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_required_action OWNER TO keycloak;

--
-- Name: fed_user_role_mapping; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.fed_user_role_mapping (
    role_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_role_mapping OWNER TO keycloak;

--
-- Name: federated_identity; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.federated_identity (
    identity_provider character varying(255) NOT NULL,
    realm_id character varying(36),
    federated_user_id character varying(255),
    federated_username character varying(255),
    token text,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.federated_identity OWNER TO keycloak;

--
-- Name: federated_user; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.federated_user (
    id character varying(255) NOT NULL,
    storage_provider_id character varying(255),
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.federated_user OWNER TO keycloak;

--
-- Name: group_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.group_attribute (
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255),
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.group_attribute OWNER TO keycloak;

--
-- Name: group_role_mapping; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.group_role_mapping (
    role_id character varying(36) NOT NULL,
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.group_role_mapping OWNER TO keycloak;

--
-- Name: identity_provider; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.identity_provider (
    internal_id character varying(36) NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    provider_alias character varying(255),
    provider_id character varying(255),
    store_token boolean DEFAULT false NOT NULL,
    authenticate_by_default boolean DEFAULT false NOT NULL,
    realm_id character varying(36),
    add_token_role boolean DEFAULT true NOT NULL,
    trust_email boolean DEFAULT false NOT NULL,
    first_broker_login_flow_id character varying(36),
    post_broker_login_flow_id character varying(36),
    provider_display_name character varying(255),
    link_only boolean DEFAULT false NOT NULL
);


ALTER TABLE public.identity_provider OWNER TO keycloak;

--
-- Name: identity_provider_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.identity_provider_config (
    identity_provider_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.identity_provider_config OWNER TO keycloak;

--
-- Name: identity_provider_mapper; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.identity_provider_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    idp_alias character varying(255) NOT NULL,
    idp_mapper_name character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.identity_provider_mapper OWNER TO keycloak;

--
-- Name: idp_mapper_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.idp_mapper_config (
    idp_mapper_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.idp_mapper_config OWNER TO keycloak;

--
-- Name: keycloak_group; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.keycloak_group (
    id character varying(36) NOT NULL,
    name character varying(255),
    parent_group character varying(36) NOT NULL,
    realm_id character varying(36)
);


ALTER TABLE public.keycloak_group OWNER TO keycloak;

--
-- Name: keycloak_role; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.keycloak_role (
    id character varying(36) NOT NULL,
    client_realm_constraint character varying(255),
    client_role boolean DEFAULT false NOT NULL,
    description character varying(255),
    name character varying(255),
    realm_id character varying(255),
    client character varying(36),
    realm character varying(36)
);


ALTER TABLE public.keycloak_role OWNER TO keycloak;

--
-- Name: migration_model; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.migration_model (
    id character varying(36) NOT NULL,
    version character varying(36),
    update_time bigint DEFAULT 0 NOT NULL
);


ALTER TABLE public.migration_model OWNER TO keycloak;

--
-- Name: offline_client_session; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.offline_client_session (
    user_session_id character varying(36) NOT NULL,
    client_id character varying(255) NOT NULL,
    offline_flag character varying(4) NOT NULL,
    "timestamp" integer,
    data text,
    client_storage_provider character varying(36) DEFAULT 'local'::character varying NOT NULL,
    external_client_id character varying(255) DEFAULT 'local'::character varying NOT NULL
);


ALTER TABLE public.offline_client_session OWNER TO keycloak;

--
-- Name: offline_user_session; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.offline_user_session (
    user_session_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    created_on integer NOT NULL,
    offline_flag character varying(4) NOT NULL,
    data text,
    last_session_refresh integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.offline_user_session OWNER TO keycloak;

--
-- Name: policy_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.policy_config (
    policy_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value text
);


ALTER TABLE public.policy_config OWNER TO keycloak;

--
-- Name: protocol_mapper; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.protocol_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    protocol character varying(255) NOT NULL,
    protocol_mapper_name character varying(255) NOT NULL,
    client_id character varying(36),
    client_scope_id character varying(36)
);


ALTER TABLE public.protocol_mapper OWNER TO keycloak;

--
-- Name: protocol_mapper_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.protocol_mapper_config (
    protocol_mapper_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.protocol_mapper_config OWNER TO keycloak;

--
-- Name: realm; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm (
    id character varying(36) NOT NULL,
    access_code_lifespan integer,
    user_action_lifespan integer,
    access_token_lifespan integer,
    account_theme character varying(255),
    admin_theme character varying(255),
    email_theme character varying(255),
    enabled boolean DEFAULT false NOT NULL,
    events_enabled boolean DEFAULT false NOT NULL,
    events_expiration bigint,
    login_theme character varying(255),
    name character varying(255),
    not_before integer,
    password_policy character varying(2550),
    registration_allowed boolean DEFAULT false NOT NULL,
    remember_me boolean DEFAULT false NOT NULL,
    reset_password_allowed boolean DEFAULT false NOT NULL,
    social boolean DEFAULT false NOT NULL,
    ssl_required character varying(255),
    sso_idle_timeout integer,
    sso_max_lifespan integer,
    update_profile_on_soc_login boolean DEFAULT false NOT NULL,
    verify_email boolean DEFAULT false NOT NULL,
    master_admin_client character varying(36),
    login_lifespan integer,
    internationalization_enabled boolean DEFAULT false NOT NULL,
    default_locale character varying(255),
    reg_email_as_username boolean DEFAULT false NOT NULL,
    admin_events_enabled boolean DEFAULT false NOT NULL,
    admin_events_details_enabled boolean DEFAULT false NOT NULL,
    edit_username_allowed boolean DEFAULT false NOT NULL,
    otp_policy_counter integer DEFAULT 0,
    otp_policy_window integer DEFAULT 1,
    otp_policy_period integer DEFAULT 30,
    otp_policy_digits integer DEFAULT 6,
    otp_policy_alg character varying(36) DEFAULT 'HmacSHA1'::character varying,
    otp_policy_type character varying(36) DEFAULT 'totp'::character varying,
    browser_flow character varying(36),
    registration_flow character varying(36),
    direct_grant_flow character varying(36),
    reset_credentials_flow character varying(36),
    client_auth_flow character varying(36),
    offline_session_idle_timeout integer DEFAULT 0,
    revoke_refresh_token boolean DEFAULT false NOT NULL,
    access_token_life_implicit integer DEFAULT 0,
    login_with_email_allowed boolean DEFAULT true NOT NULL,
    duplicate_emails_allowed boolean DEFAULT false NOT NULL,
    docker_auth_flow character varying(36),
    refresh_token_max_reuse integer DEFAULT 0,
    allow_user_managed_access boolean DEFAULT false NOT NULL,
    sso_max_lifespan_remember_me integer DEFAULT 0 NOT NULL,
    sso_idle_timeout_remember_me integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.realm OWNER TO keycloak;

--
-- Name: realm_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_attribute (
    name character varying(255) NOT NULL,
    value character varying(255),
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_attribute OWNER TO keycloak;

--
-- Name: realm_default_groups; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_default_groups (
    realm_id character varying(36) NOT NULL,
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_default_groups OWNER TO keycloak;

--
-- Name: realm_default_roles; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_default_roles (
    realm_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_default_roles OWNER TO keycloak;

--
-- Name: realm_enabled_event_types; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_enabled_event_types (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_enabled_event_types OWNER TO keycloak;

--
-- Name: realm_events_listeners; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_events_listeners (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_events_listeners OWNER TO keycloak;

--
-- Name: realm_required_credential; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_required_credential (
    type character varying(255) NOT NULL,
    form_label character varying(255),
    input boolean DEFAULT false NOT NULL,
    secret boolean DEFAULT false NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_required_credential OWNER TO keycloak;

--
-- Name: realm_smtp_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_smtp_config (
    realm_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.realm_smtp_config OWNER TO keycloak;

--
-- Name: realm_supported_locales; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.realm_supported_locales (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_supported_locales OWNER TO keycloak;

--
-- Name: redirect_uris; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.redirect_uris (
    client_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.redirect_uris OWNER TO keycloak;

--
-- Name: required_action_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.required_action_config (
    required_action_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.required_action_config OWNER TO keycloak;

--
-- Name: required_action_provider; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.required_action_provider (
    id character varying(36) NOT NULL,
    alias character varying(255),
    name character varying(255),
    realm_id character varying(36),
    enabled boolean DEFAULT false NOT NULL,
    default_action boolean DEFAULT false NOT NULL,
    provider_id character varying(255),
    priority integer
);


ALTER TABLE public.required_action_provider OWNER TO keycloak;

--
-- Name: resource_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_attribute (
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255),
    resource_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_attribute OWNER TO keycloak;

--
-- Name: resource_policy; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_policy (
    resource_id character varying(36) NOT NULL,
    policy_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_policy OWNER TO keycloak;

--
-- Name: resource_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_scope (
    resource_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_scope OWNER TO keycloak;

--
-- Name: resource_server; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_server (
    id character varying(36) NOT NULL,
    allow_rs_remote_mgmt boolean DEFAULT false NOT NULL,
    policy_enforce_mode character varying(15) NOT NULL,
    decision_strategy smallint DEFAULT 1 NOT NULL
);


ALTER TABLE public.resource_server OWNER TO keycloak;

--
-- Name: resource_server_perm_ticket; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_server_perm_ticket (
    id character varying(36) NOT NULL,
    owner character varying(255) NOT NULL,
    requester character varying(255) NOT NULL,
    created_timestamp bigint NOT NULL,
    granted_timestamp bigint,
    resource_id character varying(36) NOT NULL,
    scope_id character varying(36),
    resource_server_id character varying(36) NOT NULL,
    policy_id character varying(36)
);


ALTER TABLE public.resource_server_perm_ticket OWNER TO keycloak;

--
-- Name: resource_server_policy; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_server_policy (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    type character varying(255) NOT NULL,
    decision_strategy character varying(20),
    logic character varying(20),
    resource_server_id character varying(36) NOT NULL,
    owner character varying(255)
);


ALTER TABLE public.resource_server_policy OWNER TO keycloak;

--
-- Name: resource_server_resource; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_server_resource (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255),
    icon_uri character varying(255),
    owner character varying(255) NOT NULL,
    resource_server_id character varying(36) NOT NULL,
    owner_managed_access boolean DEFAULT false NOT NULL,
    display_name character varying(255)
);


ALTER TABLE public.resource_server_resource OWNER TO keycloak;

--
-- Name: resource_server_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_server_scope (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    icon_uri character varying(255),
    resource_server_id character varying(36) NOT NULL,
    display_name character varying(255)
);


ALTER TABLE public.resource_server_scope OWNER TO keycloak;

--
-- Name: resource_uris; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.resource_uris (
    resource_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.resource_uris OWNER TO keycloak;

--
-- Name: role_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.role_attribute (
    id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.role_attribute OWNER TO keycloak;

--
-- Name: scope_mapping; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.scope_mapping (
    client_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.scope_mapping OWNER TO keycloak;

--
-- Name: scope_policy; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.scope_policy (
    scope_id character varying(36) NOT NULL,
    policy_id character varying(36) NOT NULL
);


ALTER TABLE public.scope_policy OWNER TO keycloak;

--
-- Name: user_attribute; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_attribute (
    name character varying(255) NOT NULL,
    value character varying(255),
    user_id character varying(36) NOT NULL,
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL
);


ALTER TABLE public.user_attribute OWNER TO keycloak;

--
-- Name: user_consent; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_consent (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    user_id character varying(36) NOT NULL,
    created_date bigint,
    last_updated_date bigint,
    client_storage_provider character varying(36),
    external_client_id character varying(255)
);


ALTER TABLE public.user_consent OWNER TO keycloak;

--
-- Name: user_consent_client_scope; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_consent_client_scope (
    user_consent_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.user_consent_client_scope OWNER TO keycloak;

--
-- Name: user_entity; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_entity (
    id character varying(36) NOT NULL,
    email character varying(255),
    email_constraint character varying(255),
    email_verified boolean DEFAULT false NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    federation_link character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    realm_id character varying(255),
    username character varying(255),
    created_timestamp bigint,
    service_account_client_link character varying(255),
    not_before integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.user_entity OWNER TO keycloak;

--
-- Name: user_federation_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_federation_config (
    user_federation_provider_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.user_federation_config OWNER TO keycloak;

--
-- Name: user_federation_mapper; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_federation_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    federation_provider_id character varying(36) NOT NULL,
    federation_mapper_type character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.user_federation_mapper OWNER TO keycloak;

--
-- Name: user_federation_mapper_config; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_federation_mapper_config (
    user_federation_mapper_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.user_federation_mapper_config OWNER TO keycloak;

--
-- Name: user_federation_provider; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_federation_provider (
    id character varying(36) NOT NULL,
    changed_sync_period integer,
    display_name character varying(255),
    full_sync_period integer,
    last_sync integer,
    priority integer,
    provider_name character varying(255),
    realm_id character varying(36)
);


ALTER TABLE public.user_federation_provider OWNER TO keycloak;

--
-- Name: user_group_membership; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_group_membership (
    group_id character varying(36) NOT NULL,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.user_group_membership OWNER TO keycloak;

--
-- Name: user_required_action; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_required_action (
    user_id character varying(36) NOT NULL,
    required_action character varying(255) DEFAULT ' '::character varying NOT NULL
);


ALTER TABLE public.user_required_action OWNER TO keycloak;

--
-- Name: user_role_mapping; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_role_mapping (
    role_id character varying(255) NOT NULL,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.user_role_mapping OWNER TO keycloak;

--
-- Name: user_session; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_session (
    id character varying(36) NOT NULL,
    auth_method character varying(255),
    ip_address character varying(255),
    last_session_refresh integer,
    login_username character varying(255),
    realm_id character varying(255),
    remember_me boolean DEFAULT false NOT NULL,
    started integer,
    user_id character varying(255),
    user_session_state integer,
    broker_session_id character varying(255),
    broker_user_id character varying(255)
);


ALTER TABLE public.user_session OWNER TO keycloak;

--
-- Name: user_session_note; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.user_session_note (
    user_session character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(2048)
);


ALTER TABLE public.user_session_note OWNER TO keycloak;

--
-- Name: username_login_failure; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.username_login_failure (
    realm_id character varying(36) NOT NULL,
    username character varying(255) NOT NULL,
    failed_login_not_before integer,
    last_failure bigint,
    last_ip_failure character varying(255),
    num_failures integer
);


ALTER TABLE public.username_login_failure OWNER TO keycloak;

--
-- Name: web_origins; Type: TABLE; Schema: public; Owner: keycloak
--

CREATE TABLE public.web_origins (
    client_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.web_origins OWNER TO keycloak;

--
-- Data for Name: admin_event_entity; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.admin_event_entity (id, admin_event_time, realm_id, operation_type, auth_realm_id, auth_client_id, auth_user_id, ip_address, resource_path, representation, error, resource_type) FROM stdin;
\.


--
-- Data for Name: associated_policy; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.associated_policy (policy_id, associated_policy_id) FROM stdin;
\.


--
-- Data for Name: authentication_execution; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.authentication_execution (id, alias, authenticator, realm_id, flow_id, requirement, priority, authenticator_flow, auth_flow_id, auth_config) FROM stdin;
3284eab0-2814-4166-bfa9-b5532c9dd5ca	\N	auth-cookie	master	d48c5340-1fc3-49ae-8d3e-f663d05bc01b	2	10	f	\N	\N
ea22a35f-84ed-45b8-9016-7ea55b24aa22	\N	auth-spnego	master	d48c5340-1fc3-49ae-8d3e-f663d05bc01b	3	20	f	\N	\N
915cfb65-c1dc-4f7a-b550-d67b8fd413c8	\N	identity-provider-redirector	master	d48c5340-1fc3-49ae-8d3e-f663d05bc01b	2	25	f	\N	\N
e0e52ef9-8338-499f-a0a6-74a52079da49	\N	\N	master	d48c5340-1fc3-49ae-8d3e-f663d05bc01b	2	30	t	e3ed2e95-6bda-4897-9687-fa78ca586269	\N
ae238bfd-24cb-4b22-adf3-d465883ac7ac	\N	auth-username-password-form	master	e3ed2e95-6bda-4897-9687-fa78ca586269	0	10	f	\N	\N
df9f3d9b-42f3-4222-bb4a-3a5628989f48	\N	\N	master	e3ed2e95-6bda-4897-9687-fa78ca586269	1	20	t	95b6fb92-6151-48b7-9a81-9bb3f327e756	\N
63490a6a-8c4b-4cc0-9449-939231eab4c2	\N	conditional-user-configured	master	95b6fb92-6151-48b7-9a81-9bb3f327e756	0	10	f	\N	\N
e700776f-3295-48c8-803e-4dbed57658ce	\N	auth-otp-form	master	95b6fb92-6151-48b7-9a81-9bb3f327e756	0	20	f	\N	\N
c818b2ee-8a6b-4fbd-90df-4bda6dd6f4e4	\N	direct-grant-validate-username	master	3f90150b-ec74-4785-a812-fa9cb9182a8d	0	10	f	\N	\N
f7eb8cc4-828e-4d83-b2d0-f6bde26db1bb	\N	direct-grant-validate-password	master	3f90150b-ec74-4785-a812-fa9cb9182a8d	0	20	f	\N	\N
09b2c805-5eed-4d31-bff6-7709fd472272	\N	\N	master	3f90150b-ec74-4785-a812-fa9cb9182a8d	1	30	t	f73e70c1-8083-4d5b-a6f0-a89ba3fcc47e	\N
9f4a76dc-7670-4556-a48f-0921f329e70d	\N	conditional-user-configured	master	f73e70c1-8083-4d5b-a6f0-a89ba3fcc47e	0	10	f	\N	\N
9d9a0382-369c-42a7-ac32-497378c01765	\N	direct-grant-validate-otp	master	f73e70c1-8083-4d5b-a6f0-a89ba3fcc47e	0	20	f	\N	\N
fa948bd7-99c7-4064-b7c8-9c3cd644c82d	\N	registration-page-form	master	f9fbd9d2-e06e-4712-aa72-60fbe9de6ff2	0	10	t	9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	\N
ea5355f7-8101-48e9-ae30-8da6bfc44485	\N	registration-user-creation	master	9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	0	20	f	\N	\N
22091a01-9bac-408d-9866-a870467d0957	\N	registration-profile-action	master	9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	0	40	f	\N	\N
8bdc0fcd-c100-4fec-bae7-400a184691d0	\N	registration-password-action	master	9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	0	50	f	\N	\N
33c23775-abcb-4fb8-b787-7c12795e4aeb	\N	registration-recaptcha-action	master	9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	3	60	f	\N	\N
19a265eb-21cf-4ea3-862c-41a518f306d8	\N	reset-credentials-choose-user	master	59dc226c-f46b-48ab-9b08-7dd56c23bb6b	0	10	f	\N	\N
b74d6169-2fe8-45ba-95cc-1e6aab78ceec	\N	reset-credential-email	master	59dc226c-f46b-48ab-9b08-7dd56c23bb6b	0	20	f	\N	\N
f0f72754-35a2-4d51-a794-81f72334c57c	\N	reset-password	master	59dc226c-f46b-48ab-9b08-7dd56c23bb6b	0	30	f	\N	\N
93ef2b99-aebc-4dcb-a395-7d3afa545169	\N	\N	master	59dc226c-f46b-48ab-9b08-7dd56c23bb6b	1	40	t	ba555ba1-41f0-4542-bed6-41542bca36e3	\N
b03b99de-a0a3-41bf-b1f2-5886ee15ec96	\N	conditional-user-configured	master	ba555ba1-41f0-4542-bed6-41542bca36e3	0	10	f	\N	\N
a6d79ea4-8c3a-47d6-8d29-831fb3a4fb7a	\N	reset-otp	master	ba555ba1-41f0-4542-bed6-41542bca36e3	0	20	f	\N	\N
f62ee977-e080-4ec5-92e7-c2b34cf0f6ba	\N	client-secret	master	c9252a9a-85a1-4fa4-8b8e-97334162f9be	2	10	f	\N	\N
908d7e66-6135-4a7e-9a67-a8ea7a510786	\N	client-jwt	master	c9252a9a-85a1-4fa4-8b8e-97334162f9be	2	20	f	\N	\N
673bc5be-3bc9-4299-a8aa-6770b5aceeb0	\N	client-secret-jwt	master	c9252a9a-85a1-4fa4-8b8e-97334162f9be	2	30	f	\N	\N
9bc55b0b-0680-4db1-ba89-b900d7b4b0d5	\N	client-x509	master	c9252a9a-85a1-4fa4-8b8e-97334162f9be	2	40	f	\N	\N
eb9bee73-1a45-406a-92a3-757815785d79	\N	idp-review-profile	master	e08b0daa-aa71-41e6-b2b4-853691c5bdbc	0	10	f	\N	521e04ac-4dde-419e-82d8-36dc6c463f90
14a46a20-371d-4607-8405-7319728b9221	\N	\N	master	e08b0daa-aa71-41e6-b2b4-853691c5bdbc	0	20	t	45340d96-3bbc-4312-a327-d2d9ece82310	\N
2e7e7f78-2c78-4f92-b751-3a180148937e	\N	idp-create-user-if-unique	master	45340d96-3bbc-4312-a327-d2d9ece82310	2	10	f	\N	636230a7-64d2-4eac-93fb-b7a4934dfcf6
914a2016-4010-4a5b-85df-7e521a6d359d	\N	\N	master	45340d96-3bbc-4312-a327-d2d9ece82310	2	20	t	26c943b7-c3e7-4996-b14e-71867cab8ffc	\N
c7902db9-3f87-43b4-8629-8eaf990ba641	\N	idp-confirm-link	master	26c943b7-c3e7-4996-b14e-71867cab8ffc	0	10	f	\N	\N
736a6285-94a0-4130-97bd-703894f3ab4c	\N	\N	master	26c943b7-c3e7-4996-b14e-71867cab8ffc	0	20	t	42ba6a2b-3d64-4081-a39d-e20b64663b90	\N
29462268-304f-444e-b52e-c376b756134b	\N	idp-email-verification	master	42ba6a2b-3d64-4081-a39d-e20b64663b90	2	10	f	\N	\N
55afcfa2-ba64-4039-b055-4d4ebebdd1e3	\N	\N	master	42ba6a2b-3d64-4081-a39d-e20b64663b90	2	20	t	8aace17e-9fa2-4978-9437-9c9f2e7394d1	\N
c20d7d6e-4dea-4dd8-8f14-30502d1c1d4c	\N	idp-username-password-form	master	8aace17e-9fa2-4978-9437-9c9f2e7394d1	0	10	f	\N	\N
4f6e7334-3c98-43be-9488-21fdaa615812	\N	\N	master	8aace17e-9fa2-4978-9437-9c9f2e7394d1	1	20	t	dd95a546-ef5d-44cd-b12a-fb21be25ee3b	\N
605fb175-42b8-4089-a545-57859ab8ff2f	\N	conditional-user-configured	master	dd95a546-ef5d-44cd-b12a-fb21be25ee3b	0	10	f	\N	\N
70c86ed1-0d59-4d35-b112-5e70bb9b2560	\N	auth-otp-form	master	dd95a546-ef5d-44cd-b12a-fb21be25ee3b	0	20	f	\N	\N
b12fb250-5c41-45cd-9c00-7b70f070a4bf	\N	http-basic-authenticator	master	55db4899-1765-4fd8-a5f3-380e287aa04d	0	10	f	\N	\N
393ec259-3e25-4838-bc34-c641d5799557	\N	docker-http-basic-authenticator	master	ad877157-64ea-4b8a-87f2-2f397cb07148	0	10	f	\N	\N
d46c4183-937c-4ac9-8bd3-0c1f934d4de7	\N	no-cookie-redirect	master	3fb5ac2d-0d8b-4e6a-ab7d-478a4908a2b0	0	10	f	\N	\N
af7b33f4-02f4-4072-89ed-1ee98bb8d508	\N	\N	master	3fb5ac2d-0d8b-4e6a-ab7d-478a4908a2b0	0	20	t	421f58c8-413a-4675-b67a-dac5753f7cf6	\N
3c367da5-57a2-414a-811a-263455cfa514	\N	basic-auth	master	421f58c8-413a-4675-b67a-dac5753f7cf6	0	10	f	\N	\N
2edd4db8-1c8b-46c1-893d-1f802db8f668	\N	basic-auth-otp	master	421f58c8-413a-4675-b67a-dac5753f7cf6	3	20	f	\N	\N
411247c2-13cb-4b53-894f-2ad5f89be118	\N	auth-spnego	master	421f58c8-413a-4675-b67a-dac5753f7cf6	3	30	f	\N	\N
bd2a39d4-ac94-49ce-8183-7b473f845c92	\N	auth-cookie	companyon	2649ce2b-8114-4222-b5c8-8e114a558e89	2	10	f	\N	\N
656ad36a-17c4-446c-902d-0c12e69122bc	\N	auth-spnego	companyon	2649ce2b-8114-4222-b5c8-8e114a558e89	3	20	f	\N	\N
84096f0c-e5db-4583-b006-d5846c105279	\N	identity-provider-redirector	companyon	2649ce2b-8114-4222-b5c8-8e114a558e89	2	25	f	\N	\N
3de18293-88ab-46c9-ac4e-8e1f17e2cba1	\N	\N	companyon	2649ce2b-8114-4222-b5c8-8e114a558e89	2	30	t	fd2961dc-b4e5-4bc8-8de2-f1e8ee13022f	\N
06faa44d-a261-4fc9-b846-e63c372a457f	\N	auth-username-password-form	companyon	fd2961dc-b4e5-4bc8-8de2-f1e8ee13022f	0	10	f	\N	\N
52b46e08-5426-4221-9c2b-3de95a7917bf	\N	\N	companyon	fd2961dc-b4e5-4bc8-8de2-f1e8ee13022f	1	20	t	17f342ea-cab8-44b1-acd8-eb9a3481a7a8	\N
5ce3af2e-69cc-4e8f-b62d-05f1977cb904	\N	conditional-user-configured	companyon	17f342ea-cab8-44b1-acd8-eb9a3481a7a8	0	10	f	\N	\N
0d8be98a-2b38-45df-aa50-54d4cd1782f9	\N	auth-otp-form	companyon	17f342ea-cab8-44b1-acd8-eb9a3481a7a8	0	20	f	\N	\N
ebf1191e-2acd-4102-a41e-7f840f9a41b9	\N	direct-grant-validate-username	companyon	b982bc77-2af2-4691-bda9-e0e42840ba20	0	10	f	\N	\N
563f2be0-4319-49fd-b491-72c9a8578a0b	\N	direct-grant-validate-password	companyon	b982bc77-2af2-4691-bda9-e0e42840ba20	0	20	f	\N	\N
93560962-667c-465d-bb58-95a9b1469349	\N	\N	companyon	b982bc77-2af2-4691-bda9-e0e42840ba20	1	30	t	6bebf40d-4353-48e9-85d3-efb1a66538e8	\N
d04ecc93-5521-4d2c-828f-94be3611be40	\N	conditional-user-configured	companyon	6bebf40d-4353-48e9-85d3-efb1a66538e8	0	10	f	\N	\N
d55ccedc-feab-446a-8bfa-5deea3971d2e	\N	direct-grant-validate-otp	companyon	6bebf40d-4353-48e9-85d3-efb1a66538e8	0	20	f	\N	\N
cfe49da5-d840-4749-aa4e-27e539f0849d	\N	registration-page-form	companyon	bb64b3cf-27be-4f9e-b2f9-faf8741d012d	0	10	t	ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	\N
bf2c91e4-4e89-49a1-8749-de9b4bc95210	\N	registration-user-creation	companyon	ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	0	20	f	\N	\N
1d5340b3-fbb4-483e-99dc-830b9d9135e5	\N	registration-profile-action	companyon	ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	0	40	f	\N	\N
73286172-d972-4991-96d8-5c0784e6e5a6	\N	registration-password-action	companyon	ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	0	50	f	\N	\N
e68e020c-1d07-4eab-9615-49312bae9235	\N	registration-recaptcha-action	companyon	ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	3	60	f	\N	\N
f1e4c8b2-8078-49b7-b720-45e3e0ef858b	\N	reset-credentials-choose-user	companyon	7e59a0a5-66de-4a37-9ba3-f5107797782a	0	10	f	\N	\N
00a7cdb4-db16-4df6-afc1-62dab830f1af	\N	reset-credential-email	companyon	7e59a0a5-66de-4a37-9ba3-f5107797782a	0	20	f	\N	\N
54ce4a85-1ddb-4d8b-a162-18e987b7994a	\N	reset-password	companyon	7e59a0a5-66de-4a37-9ba3-f5107797782a	0	30	f	\N	\N
6035ba22-efa6-4f9f-8436-be90a1d8629c	\N	\N	companyon	7e59a0a5-66de-4a37-9ba3-f5107797782a	1	40	t	b8812201-14d9-4cc3-af22-abfa45800e7a	\N
121af3fa-2854-4f73-9693-5ae193825d1b	\N	conditional-user-configured	companyon	b8812201-14d9-4cc3-af22-abfa45800e7a	0	10	f	\N	\N
e3d05681-4674-4e1f-b29d-196d35399e02	\N	reset-otp	companyon	b8812201-14d9-4cc3-af22-abfa45800e7a	0	20	f	\N	\N
ad19e05a-c543-4506-87e9-329cb0511c8c	\N	client-secret	companyon	b1979cd8-80cb-4199-96e1-d8fdad08b375	2	10	f	\N	\N
56290941-4014-495e-b3dc-94e5497a4fa0	\N	client-jwt	companyon	b1979cd8-80cb-4199-96e1-d8fdad08b375	2	20	f	\N	\N
c111ed4f-1228-4f26-bd5b-3337825e92fb	\N	client-secret-jwt	companyon	b1979cd8-80cb-4199-96e1-d8fdad08b375	2	30	f	\N	\N
ef0da98d-0c0f-42e5-add2-1e707fd32c59	\N	client-x509	companyon	b1979cd8-80cb-4199-96e1-d8fdad08b375	2	40	f	\N	\N
f13e10cc-429a-4627-89b9-44ddd7ebce66	\N	idp-review-profile	companyon	eaaac0fe-c1c1-418c-a1a0-e36ba5040823	0	10	f	\N	c25a0abc-c9c5-49c7-afa8-c0b38259dead
6f8694a2-0ebc-4601-9619-0b55ccb27656	\N	\N	companyon	eaaac0fe-c1c1-418c-a1a0-e36ba5040823	0	20	t	44922e19-2e9d-4bc3-ac9d-97dfe888e1c9	\N
0b245e0c-2f9e-4039-9dff-21a58a8f8a07	\N	idp-create-user-if-unique	companyon	44922e19-2e9d-4bc3-ac9d-97dfe888e1c9	2	10	f	\N	e1a61cea-e63a-4fdd-86c9-cc0d7e574ebe
c1a1155e-93b8-4c19-9920-c9a41f081d56	\N	\N	companyon	44922e19-2e9d-4bc3-ac9d-97dfe888e1c9	2	20	t	2906e313-bb14-43ec-b5b1-48ddcc4f8ac6	\N
c06228ba-f5f2-4bbd-a190-b23a32026dd3	\N	idp-confirm-link	companyon	2906e313-bb14-43ec-b5b1-48ddcc4f8ac6	0	10	f	\N	\N
24a6b15d-a51a-42b8-9862-bd6e623f9a89	\N	\N	companyon	2906e313-bb14-43ec-b5b1-48ddcc4f8ac6	0	20	t	1619f882-3228-4ea4-918f-81ecc43af22c	\N
3f2055c1-e6c6-419d-9ce1-b6dcf25d32c2	\N	idp-email-verification	companyon	1619f882-3228-4ea4-918f-81ecc43af22c	2	10	f	\N	\N
2ea1ad4e-257b-4264-96cb-28c6df75f59d	\N	\N	companyon	1619f882-3228-4ea4-918f-81ecc43af22c	2	20	t	066480b6-4be7-4462-9974-26b037a1f762	\N
d5be1171-d7ce-4760-83c5-952e0616d76e	\N	idp-username-password-form	companyon	066480b6-4be7-4462-9974-26b037a1f762	0	10	f	\N	\N
f74e531c-c7f6-4423-b4f3-14ad891ed58e	\N	\N	companyon	066480b6-4be7-4462-9974-26b037a1f762	1	20	t	09089c44-bc88-4472-bcca-42db276ec5fd	\N
9aec9645-fe36-46ae-898f-889813e20690	\N	conditional-user-configured	companyon	09089c44-bc88-4472-bcca-42db276ec5fd	0	10	f	\N	\N
a4fb0b41-cbab-4d92-bb6d-a847a34f5884	\N	auth-otp-form	companyon	09089c44-bc88-4472-bcca-42db276ec5fd	0	20	f	\N	\N
8b8c0e87-b770-4a8f-93d0-c5ba719b2a18	\N	http-basic-authenticator	companyon	e8fd8e29-adf5-4ab9-bbfe-9980be7cc4ac	0	10	f	\N	\N
ace66112-4ac7-4306-a298-b6487c25e20a	\N	docker-http-basic-authenticator	companyon	261090cf-a13d-406e-b99e-91aace2ae74a	0	10	f	\N	\N
07390056-a1ae-4c34-9c18-9effda31e756	\N	no-cookie-redirect	companyon	89e4812a-7fde-4ac2-979e-c040f5cf8c7b	0	10	f	\N	\N
c635fa67-c0f5-4684-8cd9-131277f7206a	\N	\N	companyon	89e4812a-7fde-4ac2-979e-c040f5cf8c7b	0	20	t	a13bdbc0-9e04-4a74-8539-9d0b67039376	\N
ff89fa57-f51a-4d93-80b0-de7ae12f95ad	\N	basic-auth	companyon	a13bdbc0-9e04-4a74-8539-9d0b67039376	0	10	f	\N	\N
07093623-3472-4329-9002-8d27b77c3a5d	\N	basic-auth-otp	companyon	a13bdbc0-9e04-4a74-8539-9d0b67039376	3	20	f	\N	\N
53da44db-cad0-4e4c-a551-87d4b30370c7	\N	auth-spnego	companyon	a13bdbc0-9e04-4a74-8539-9d0b67039376	3	30	f	\N	\N
\.


--
-- Data for Name: authentication_flow; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.authentication_flow (id, alias, description, realm_id, provider_id, top_level, built_in) FROM stdin;
d48c5340-1fc3-49ae-8d3e-f663d05bc01b	browser	browser based authentication	master	basic-flow	t	t
e3ed2e95-6bda-4897-9687-fa78ca586269	forms	Username, password, otp and other auth forms.	master	basic-flow	f	t
95b6fb92-6151-48b7-9a81-9bb3f327e756	Browser - Conditional OTP	Flow to determine if the OTP is required for the authentication	master	basic-flow	f	t
3f90150b-ec74-4785-a812-fa9cb9182a8d	direct grant	OpenID Connect Resource Owner Grant	master	basic-flow	t	t
f73e70c1-8083-4d5b-a6f0-a89ba3fcc47e	Direct Grant - Conditional OTP	Flow to determine if the OTP is required for the authentication	master	basic-flow	f	t
f9fbd9d2-e06e-4712-aa72-60fbe9de6ff2	registration	registration flow	master	basic-flow	t	t
9dfb5539-8e42-4e3d-a7d9-2bc54706dca7	registration form	registration form	master	form-flow	f	t
59dc226c-f46b-48ab-9b08-7dd56c23bb6b	reset credentials	Reset credentials for a user if they forgot their password or something	master	basic-flow	t	t
ba555ba1-41f0-4542-bed6-41542bca36e3	Reset - Conditional OTP	Flow to determine if the OTP should be reset or not. Set to REQUIRED to force.	master	basic-flow	f	t
c9252a9a-85a1-4fa4-8b8e-97334162f9be	clients	Base authentication for clients	master	client-flow	t	t
e08b0daa-aa71-41e6-b2b4-853691c5bdbc	first broker login	Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account	master	basic-flow	t	t
45340d96-3bbc-4312-a327-d2d9ece82310	User creation or linking	Flow for the existing/non-existing user alternatives	master	basic-flow	f	t
26c943b7-c3e7-4996-b14e-71867cab8ffc	Handle Existing Account	Handle what to do if there is existing account with same email/username like authenticated identity provider	master	basic-flow	f	t
42ba6a2b-3d64-4081-a39d-e20b64663b90	Account verification options	Method with which to verity the existing account	master	basic-flow	f	t
8aace17e-9fa2-4978-9437-9c9f2e7394d1	Verify Existing Account by Re-authentication	Reauthentication of existing account	master	basic-flow	f	t
dd95a546-ef5d-44cd-b12a-fb21be25ee3b	First broker login - Conditional OTP	Flow to determine if the OTP is required for the authentication	master	basic-flow	f	t
55db4899-1765-4fd8-a5f3-380e287aa04d	saml ecp	SAML ECP Profile Authentication Flow	master	basic-flow	t	t
ad877157-64ea-4b8a-87f2-2f397cb07148	docker auth	Used by Docker clients to authenticate against the IDP	master	basic-flow	t	t
3fb5ac2d-0d8b-4e6a-ab7d-478a4908a2b0	http challenge	An authentication flow based on challenge-response HTTP Authentication Schemes	master	basic-flow	t	t
421f58c8-413a-4675-b67a-dac5753f7cf6	Authentication Options	Authentication options.	master	basic-flow	f	t
2649ce2b-8114-4222-b5c8-8e114a558e89	browser	browser based authentication	companyon	basic-flow	t	t
fd2961dc-b4e5-4bc8-8de2-f1e8ee13022f	forms	Username, password, otp and other auth forms.	companyon	basic-flow	f	t
17f342ea-cab8-44b1-acd8-eb9a3481a7a8	Browser - Conditional OTP	Flow to determine if the OTP is required for the authentication	companyon	basic-flow	f	t
b982bc77-2af2-4691-bda9-e0e42840ba20	direct grant	OpenID Connect Resource Owner Grant	companyon	basic-flow	t	t
6bebf40d-4353-48e9-85d3-efb1a66538e8	Direct Grant - Conditional OTP	Flow to determine if the OTP is required for the authentication	companyon	basic-flow	f	t
bb64b3cf-27be-4f9e-b2f9-faf8741d012d	registration	registration flow	companyon	basic-flow	t	t
ba3f7821-ede3-488c-b7ad-6ddd6c540bbe	registration form	registration form	companyon	form-flow	f	t
7e59a0a5-66de-4a37-9ba3-f5107797782a	reset credentials	Reset credentials for a user if they forgot their password or something	companyon	basic-flow	t	t
b8812201-14d9-4cc3-af22-abfa45800e7a	Reset - Conditional OTP	Flow to determine if the OTP should be reset or not. Set to REQUIRED to force.	companyon	basic-flow	f	t
b1979cd8-80cb-4199-96e1-d8fdad08b375	clients	Base authentication for clients	companyon	client-flow	t	t
eaaac0fe-c1c1-418c-a1a0-e36ba5040823	first broker login	Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account	companyon	basic-flow	t	t
44922e19-2e9d-4bc3-ac9d-97dfe888e1c9	User creation or linking	Flow for the existing/non-existing user alternatives	companyon	basic-flow	f	t
2906e313-bb14-43ec-b5b1-48ddcc4f8ac6	Handle Existing Account	Handle what to do if there is existing account with same email/username like authenticated identity provider	companyon	basic-flow	f	t
1619f882-3228-4ea4-918f-81ecc43af22c	Account verification options	Method with which to verity the existing account	companyon	basic-flow	f	t
066480b6-4be7-4462-9974-26b037a1f762	Verify Existing Account by Re-authentication	Reauthentication of existing account	companyon	basic-flow	f	t
09089c44-bc88-4472-bcca-42db276ec5fd	First broker login - Conditional OTP	Flow to determine if the OTP is required for the authentication	companyon	basic-flow	f	t
e8fd8e29-adf5-4ab9-bbfe-9980be7cc4ac	saml ecp	SAML ECP Profile Authentication Flow	companyon	basic-flow	t	t
261090cf-a13d-406e-b99e-91aace2ae74a	docker auth	Used by Docker clients to authenticate against the IDP	companyon	basic-flow	t	t
89e4812a-7fde-4ac2-979e-c040f5cf8c7b	http challenge	An authentication flow based on challenge-response HTTP Authentication Schemes	companyon	basic-flow	t	t
a13bdbc0-9e04-4a74-8539-9d0b67039376	Authentication Options	Authentication options.	companyon	basic-flow	f	t
\.


--
-- Data for Name: authenticator_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.authenticator_config (id, alias, realm_id) FROM stdin;
521e04ac-4dde-419e-82d8-36dc6c463f90	review profile config	master
636230a7-64d2-4eac-93fb-b7a4934dfcf6	create unique user config	master
c25a0abc-c9c5-49c7-afa8-c0b38259dead	review profile config	companyon
e1a61cea-e63a-4fdd-86c9-cc0d7e574ebe	create unique user config	companyon
\.


--
-- Data for Name: authenticator_config_entry; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.authenticator_config_entry (authenticator_id, value, name) FROM stdin;
521e04ac-4dde-419e-82d8-36dc6c463f90	missing	update.profile.on.first.login
636230a7-64d2-4eac-93fb-b7a4934dfcf6	false	require.password.update.after.registration
c25a0abc-c9c5-49c7-afa8-c0b38259dead	missing	update.profile.on.first.login
e1a61cea-e63a-4fdd-86c9-cc0d7e574ebe	false	require.password.update.after.registration
\.


--
-- Data for Name: broker_link; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.broker_link (identity_provider, storage_provider_id, realm_id, broker_user_id, broker_username, token, user_id) FROM stdin;
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client (id, enabled, full_scope_allowed, client_id, not_before, public_client, secret, base_url, bearer_only, management_url, surrogate_auth_required, realm_id, protocol, node_rereg_timeout, frontchannel_logout, consent_required, name, service_accounts_enabled, client_authenticator_type, root_url, description, registration_token, standard_flow_enabled, implicit_flow_enabled, direct_access_grants_enabled, always_display_in_console) FROM stdin;
4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	t	master-realm	0	f	912e4c45-30df-4266-beac-629e31bf4c7f	\N	t	\N	f	master	\N	0	f	f	master Realm	f	client-secret	\N	\N	\N	t	f	f	f
25761e29-96d5-4661-a01e-744abbe404b2	t	f	account	0	f	1bd91b5d-183b-471f-9f06-85c525d6112c	/realms/master/account/	f	\N	f	master	openid-connect	0	f	f	${client_account}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
420f22e1-f0e1-407e-af56-07e737bba6bd	t	f	account-console	0	t	79701c39-3730-4310-847a-7aedb45fa2cc	/realms/master/account/	f	\N	f	master	openid-connect	0	f	f	${client_account-console}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	t	f	broker	0	f	e8d592a0-629b-4a5f-bae7-aa70dc53232e	\N	f	\N	f	master	openid-connect	0	f	f	${client_broker}	f	client-secret	\N	\N	\N	t	f	f	f
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	t	f	security-admin-console	0	t	c01fc99a-c3b5-4d2d-88fd-df8c3d958866	/admin/master/console/	f	\N	f	master	openid-connect	0	f	f	${client_security-admin-console}	f	client-secret	${authAdminUrl}	\N	\N	t	f	f	f
4aec7650-f007-4d5f-9e6b-e08c3151e10a	t	f	admin-cli	0	t	44a42135-0e88-4ca6-9fa2-dc814e4e2e3c	\N	f	\N	f	master	openid-connect	0	f	f	${client_admin-cli}	f	client-secret	\N	\N	\N	f	f	t	f
dfd71606-7771-404f-834d-1dfbb4cfbd05	t	t	companyon-realm	0	f	f2404acf-c3a9-401e-9c88-42561d7e8d72	\N	t	\N	f	master	\N	0	f	f	companyon Realm	f	client-secret	\N	\N	\N	t	f	f	f
91591f15-7809-42f6-ba40-659ffc073ec7	t	f	realm-management	0	f	4f1a3711-cc8f-45d0-996f-52f139f41472	\N	t	\N	f	companyon	openid-connect	0	f	f	${client_realm-management}	f	client-secret	\N	\N	\N	t	f	f	f
3b5949c7-439f-4733-9bc3-16515381a9a1	t	f	account	0	f	4b768432-dd19-458d-ad84-8ebfb7bba618	/realms/companyon/account/	f	\N	f	companyon	openid-connect	0	f	f	${client_account}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
bc4fcf66-a2f7-4363-988e-cef18116ab40	t	f	account-console	0	t	52c8fa6e-2525-4966-bd11-e64410f41db4	/realms/companyon/account/	f	\N	f	companyon	openid-connect	0	f	f	${client_account-console}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
ee58eec6-3349-4569-9dab-ce3563f60b43	t	f	broker	0	f	b13f6b39-1ee6-4860-8a73-ea706cfb4bfb	\N	f	\N	f	companyon	openid-connect	0	f	f	${client_broker}	f	client-secret	\N	\N	\N	t	f	f	f
b37d425e-5951-4065-b582-dbefc77d5b61	t	f	security-admin-console	0	t	551d01d7-7ae8-4afb-905a-eb0bd6372b3f	/admin/companyon/console/	f	\N	f	companyon	openid-connect	0	f	f	${client_security-admin-console}	f	client-secret	${authAdminUrl}	\N	\N	t	f	f	f
3bfc1fd2-44c8-4491-9f78-6f2d22886def	t	f	admin-cli	0	t	d1514ac8-4105-4be2-a557-7df01b7f7d42	\N	f	\N	f	companyon	openid-connect	0	f	f	${client_admin-cli}	f	client-secret	\N	\N	\N	f	f	t	f
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	t	t	companyon-fe	0	t	26f784d0-a9c8-478e-9023-78c67d245dd2	\N	f	\N	f	companyon	openid-connect	-1	f	f	\N	f	client-secret	\N	\N	\N	t	f	t	f
\.


--
-- Data for Name: client_attributes; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_attributes (client_id, value, name) FROM stdin;
420f22e1-f0e1-407e-af56-07e737bba6bd	S256	pkce.code.challenge.method
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	S256	pkce.code.challenge.method
bc4fcf66-a2f7-4363-988e-cef18116ab40	S256	pkce.code.challenge.method
b37d425e-5951-4065-b582-dbefc77d5b61	S256	pkce.code.challenge.method
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.server.signature
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.server.signature.keyinfo.ext
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.assertion.signature
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.client.signature
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.encrypt
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.authnstatement
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.onetimeuse.condition
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml_force_name_id_format
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.multivalued.roles
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	saml.force.post.binding
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	exclude.session.state.from.auth.response
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	tls.client.certificate.bound.access.tokens
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	false	display.on.consent.screen
\.


--
-- Data for Name: client_auth_flow_bindings; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_auth_flow_bindings (client_id, flow_id, binding_name) FROM stdin;
\.


--
-- Data for Name: client_default_roles; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_default_roles (client_id, role_id) FROM stdin;
25761e29-96d5-4661-a01e-744abbe404b2	524c815d-7089-4723-be67-fad4e743e865
25761e29-96d5-4661-a01e-744abbe404b2	027dd772-2415-4346-a5f2-3f2ed5d2424a
3b5949c7-439f-4733-9bc3-16515381a9a1	97b672e7-7048-4387-826b-94ae920086c5
3b5949c7-439f-4733-9bc3-16515381a9a1	657edb13-4e30-46fe-ad93-e8b0663ea6eb
\.


--
-- Data for Name: client_initial_access; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_initial_access (id, realm_id, "timestamp", expiration, count, remaining_count) FROM stdin;
\.


--
-- Data for Name: client_node_registrations; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_node_registrations (client_id, value, name) FROM stdin;
\.


--
-- Data for Name: client_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_scope (id, name, realm_id, description, protocol) FROM stdin;
b7bfac75-fe18-4683-8d2a-00c66ee86c26	offline_access	master	OpenID Connect built-in scope: offline_access	openid-connect
76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	role_list	master	SAML role list	saml
b3b85baf-867f-4ae1-ae51-b4e1037c49f3	profile	master	OpenID Connect built-in scope: profile	openid-connect
986138f6-4287-4215-ae66-bebda186a7d5	email	master	OpenID Connect built-in scope: email	openid-connect
2816a579-86b3-4afb-ba5e-f6bdf0339b9d	address	master	OpenID Connect built-in scope: address	openid-connect
0873f689-ea5f-4b35-990f-9f08976f9fe7	phone	master	OpenID Connect built-in scope: phone	openid-connect
5dae4cca-5ac3-4e56-ac42-29244a3e560a	roles	master	OpenID Connect scope for add user roles to the access token	openid-connect
f4b7809a-43ce-4884-ab68-fc88aacde2f0	web-origins	master	OpenID Connect scope for add allowed web origins to the access token	openid-connect
0aae6106-7ce2-49a9-b359-eea980b01322	microprofile-jwt	master	Microprofile - JWT built-in scope	openid-connect
a6377b76-d5cc-46bb-851a-be5024fdcdf6	offline_access	companyon	OpenID Connect built-in scope: offline_access	openid-connect
7517764d-146d-4f29-87d4-230df456e356	role_list	companyon	SAML role list	saml
3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	profile	companyon	OpenID Connect built-in scope: profile	openid-connect
b50a942b-6668-4f80-a7ca-83ae6e3a959a	email	companyon	OpenID Connect built-in scope: email	openid-connect
65c63377-ca9b-4724-aea4-e37022dd4fc9	address	companyon	OpenID Connect built-in scope: address	openid-connect
104ef280-4d18-4d17-87a0-c1c71edea2a3	phone	companyon	OpenID Connect built-in scope: phone	openid-connect
28bb5202-fba9-43d9-9145-a21f3be461fb	roles	companyon	OpenID Connect scope for add user roles to the access token	openid-connect
a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	web-origins	companyon	OpenID Connect scope for add allowed web origins to the access token	openid-connect
1e1e360f-b93d-49e8-b936-17e4da3cc021	microprofile-jwt	companyon	Microprofile - JWT built-in scope	openid-connect
\.


--
-- Data for Name: client_scope_attributes; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_scope_attributes (scope_id, value, name) FROM stdin;
b7bfac75-fe18-4683-8d2a-00c66ee86c26	true	display.on.consent.screen
b7bfac75-fe18-4683-8d2a-00c66ee86c26	${offlineAccessScopeConsentText}	consent.screen.text
76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	true	display.on.consent.screen
76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	${samlRoleListScopeConsentText}	consent.screen.text
b3b85baf-867f-4ae1-ae51-b4e1037c49f3	true	display.on.consent.screen
b3b85baf-867f-4ae1-ae51-b4e1037c49f3	${profileScopeConsentText}	consent.screen.text
b3b85baf-867f-4ae1-ae51-b4e1037c49f3	true	include.in.token.scope
986138f6-4287-4215-ae66-bebda186a7d5	true	display.on.consent.screen
986138f6-4287-4215-ae66-bebda186a7d5	${emailScopeConsentText}	consent.screen.text
986138f6-4287-4215-ae66-bebda186a7d5	true	include.in.token.scope
2816a579-86b3-4afb-ba5e-f6bdf0339b9d	true	display.on.consent.screen
2816a579-86b3-4afb-ba5e-f6bdf0339b9d	${addressScopeConsentText}	consent.screen.text
2816a579-86b3-4afb-ba5e-f6bdf0339b9d	true	include.in.token.scope
0873f689-ea5f-4b35-990f-9f08976f9fe7	true	display.on.consent.screen
0873f689-ea5f-4b35-990f-9f08976f9fe7	${phoneScopeConsentText}	consent.screen.text
0873f689-ea5f-4b35-990f-9f08976f9fe7	true	include.in.token.scope
5dae4cca-5ac3-4e56-ac42-29244a3e560a	true	display.on.consent.screen
5dae4cca-5ac3-4e56-ac42-29244a3e560a	${rolesScopeConsentText}	consent.screen.text
5dae4cca-5ac3-4e56-ac42-29244a3e560a	false	include.in.token.scope
f4b7809a-43ce-4884-ab68-fc88aacde2f0	false	display.on.consent.screen
f4b7809a-43ce-4884-ab68-fc88aacde2f0		consent.screen.text
f4b7809a-43ce-4884-ab68-fc88aacde2f0	false	include.in.token.scope
0aae6106-7ce2-49a9-b359-eea980b01322	false	display.on.consent.screen
0aae6106-7ce2-49a9-b359-eea980b01322	true	include.in.token.scope
a6377b76-d5cc-46bb-851a-be5024fdcdf6	true	display.on.consent.screen
a6377b76-d5cc-46bb-851a-be5024fdcdf6	${offlineAccessScopeConsentText}	consent.screen.text
7517764d-146d-4f29-87d4-230df456e356	true	display.on.consent.screen
7517764d-146d-4f29-87d4-230df456e356	${samlRoleListScopeConsentText}	consent.screen.text
3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	true	display.on.consent.screen
3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	${profileScopeConsentText}	consent.screen.text
3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	true	include.in.token.scope
b50a942b-6668-4f80-a7ca-83ae6e3a959a	true	display.on.consent.screen
b50a942b-6668-4f80-a7ca-83ae6e3a959a	${emailScopeConsentText}	consent.screen.text
b50a942b-6668-4f80-a7ca-83ae6e3a959a	true	include.in.token.scope
65c63377-ca9b-4724-aea4-e37022dd4fc9	true	display.on.consent.screen
65c63377-ca9b-4724-aea4-e37022dd4fc9	${addressScopeConsentText}	consent.screen.text
65c63377-ca9b-4724-aea4-e37022dd4fc9	true	include.in.token.scope
104ef280-4d18-4d17-87a0-c1c71edea2a3	true	display.on.consent.screen
104ef280-4d18-4d17-87a0-c1c71edea2a3	${phoneScopeConsentText}	consent.screen.text
104ef280-4d18-4d17-87a0-c1c71edea2a3	true	include.in.token.scope
28bb5202-fba9-43d9-9145-a21f3be461fb	true	display.on.consent.screen
28bb5202-fba9-43d9-9145-a21f3be461fb	${rolesScopeConsentText}	consent.screen.text
28bb5202-fba9-43d9-9145-a21f3be461fb	false	include.in.token.scope
a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	false	display.on.consent.screen
a8957cd5-e000-4abc-89e8-f67ad7fbf7aa		consent.screen.text
a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	false	include.in.token.scope
1e1e360f-b93d-49e8-b936-17e4da3cc021	false	display.on.consent.screen
1e1e360f-b93d-49e8-b936-17e4da3cc021	true	include.in.token.scope
\.


--
-- Data for Name: client_scope_client; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_scope_client (client_id, scope_id, default_scope) FROM stdin;
25761e29-96d5-4661-a01e-744abbe404b2	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
420f22e1-f0e1-407e-af56-07e737bba6bd	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
4aec7650-f007-4d5f-9e6b-e08c3151e10a	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
4acfb22d-b9a5-4cf7-b885-c2570bae9106	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
25761e29-96d5-4661-a01e-744abbe404b2	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
25761e29-96d5-4661-a01e-744abbe404b2	986138f6-4287-4215-ae66-bebda186a7d5	t
25761e29-96d5-4661-a01e-744abbe404b2	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
25761e29-96d5-4661-a01e-744abbe404b2	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
25761e29-96d5-4661-a01e-744abbe404b2	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
25761e29-96d5-4661-a01e-744abbe404b2	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
25761e29-96d5-4661-a01e-744abbe404b2	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
25761e29-96d5-4661-a01e-744abbe404b2	0aae6106-7ce2-49a9-b359-eea980b01322	f
420f22e1-f0e1-407e-af56-07e737bba6bd	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
420f22e1-f0e1-407e-af56-07e737bba6bd	986138f6-4287-4215-ae66-bebda186a7d5	t
420f22e1-f0e1-407e-af56-07e737bba6bd	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
420f22e1-f0e1-407e-af56-07e737bba6bd	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
420f22e1-f0e1-407e-af56-07e737bba6bd	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
420f22e1-f0e1-407e-af56-07e737bba6bd	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
420f22e1-f0e1-407e-af56-07e737bba6bd	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
420f22e1-f0e1-407e-af56-07e737bba6bd	0aae6106-7ce2-49a9-b359-eea980b01322	f
4aec7650-f007-4d5f-9e6b-e08c3151e10a	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
4aec7650-f007-4d5f-9e6b-e08c3151e10a	986138f6-4287-4215-ae66-bebda186a7d5	t
4aec7650-f007-4d5f-9e6b-e08c3151e10a	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
4aec7650-f007-4d5f-9e6b-e08c3151e10a	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
4aec7650-f007-4d5f-9e6b-e08c3151e10a	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
4aec7650-f007-4d5f-9e6b-e08c3151e10a	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
4aec7650-f007-4d5f-9e6b-e08c3151e10a	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
4aec7650-f007-4d5f-9e6b-e08c3151e10a	0aae6106-7ce2-49a9-b359-eea980b01322	f
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	986138f6-4287-4215-ae66-bebda186a7d5	t
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
e6bbda91-de2b-4957-912e-2dfa7dc9a69e	0aae6106-7ce2-49a9-b359-eea980b01322	f
4acfb22d-b9a5-4cf7-b885-c2570bae9106	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
4acfb22d-b9a5-4cf7-b885-c2570bae9106	986138f6-4287-4215-ae66-bebda186a7d5	t
4acfb22d-b9a5-4cf7-b885-c2570bae9106	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
4acfb22d-b9a5-4cf7-b885-c2570bae9106	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
4acfb22d-b9a5-4cf7-b885-c2570bae9106	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
4acfb22d-b9a5-4cf7-b885-c2570bae9106	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
4acfb22d-b9a5-4cf7-b885-c2570bae9106	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
4acfb22d-b9a5-4cf7-b885-c2570bae9106	0aae6106-7ce2-49a9-b359-eea980b01322	f
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	986138f6-4287-4215-ae66-bebda186a7d5	t
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	0aae6106-7ce2-49a9-b359-eea980b01322	f
dfd71606-7771-404f-834d-1dfbb4cfbd05	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
dfd71606-7771-404f-834d-1dfbb4cfbd05	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
dfd71606-7771-404f-834d-1dfbb4cfbd05	986138f6-4287-4215-ae66-bebda186a7d5	t
dfd71606-7771-404f-834d-1dfbb4cfbd05	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
dfd71606-7771-404f-834d-1dfbb4cfbd05	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
dfd71606-7771-404f-834d-1dfbb4cfbd05	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
dfd71606-7771-404f-834d-1dfbb4cfbd05	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
dfd71606-7771-404f-834d-1dfbb4cfbd05	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
dfd71606-7771-404f-834d-1dfbb4cfbd05	0aae6106-7ce2-49a9-b359-eea980b01322	f
3b5949c7-439f-4733-9bc3-16515381a9a1	7517764d-146d-4f29-87d4-230df456e356	t
bc4fcf66-a2f7-4363-988e-cef18116ab40	7517764d-146d-4f29-87d4-230df456e356	t
3bfc1fd2-44c8-4491-9f78-6f2d22886def	7517764d-146d-4f29-87d4-230df456e356	t
ee58eec6-3349-4569-9dab-ce3563f60b43	7517764d-146d-4f29-87d4-230df456e356	t
91591f15-7809-42f6-ba40-659ffc073ec7	7517764d-146d-4f29-87d4-230df456e356	t
b37d425e-5951-4065-b582-dbefc77d5b61	7517764d-146d-4f29-87d4-230df456e356	t
3b5949c7-439f-4733-9bc3-16515381a9a1	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
3b5949c7-439f-4733-9bc3-16515381a9a1	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
3b5949c7-439f-4733-9bc3-16515381a9a1	28bb5202-fba9-43d9-9145-a21f3be461fb	t
3b5949c7-439f-4733-9bc3-16515381a9a1	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
3b5949c7-439f-4733-9bc3-16515381a9a1	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
3b5949c7-439f-4733-9bc3-16515381a9a1	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
3b5949c7-439f-4733-9bc3-16515381a9a1	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
3b5949c7-439f-4733-9bc3-16515381a9a1	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
bc4fcf66-a2f7-4363-988e-cef18116ab40	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
bc4fcf66-a2f7-4363-988e-cef18116ab40	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
bc4fcf66-a2f7-4363-988e-cef18116ab40	28bb5202-fba9-43d9-9145-a21f3be461fb	t
bc4fcf66-a2f7-4363-988e-cef18116ab40	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
bc4fcf66-a2f7-4363-988e-cef18116ab40	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
bc4fcf66-a2f7-4363-988e-cef18116ab40	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
bc4fcf66-a2f7-4363-988e-cef18116ab40	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
bc4fcf66-a2f7-4363-988e-cef18116ab40	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
3bfc1fd2-44c8-4491-9f78-6f2d22886def	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
3bfc1fd2-44c8-4491-9f78-6f2d22886def	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
3bfc1fd2-44c8-4491-9f78-6f2d22886def	28bb5202-fba9-43d9-9145-a21f3be461fb	t
3bfc1fd2-44c8-4491-9f78-6f2d22886def	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
3bfc1fd2-44c8-4491-9f78-6f2d22886def	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
3bfc1fd2-44c8-4491-9f78-6f2d22886def	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
3bfc1fd2-44c8-4491-9f78-6f2d22886def	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
3bfc1fd2-44c8-4491-9f78-6f2d22886def	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
ee58eec6-3349-4569-9dab-ce3563f60b43	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
ee58eec6-3349-4569-9dab-ce3563f60b43	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
ee58eec6-3349-4569-9dab-ce3563f60b43	28bb5202-fba9-43d9-9145-a21f3be461fb	t
ee58eec6-3349-4569-9dab-ce3563f60b43	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
ee58eec6-3349-4569-9dab-ce3563f60b43	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
ee58eec6-3349-4569-9dab-ce3563f60b43	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
ee58eec6-3349-4569-9dab-ce3563f60b43	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
ee58eec6-3349-4569-9dab-ce3563f60b43	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
91591f15-7809-42f6-ba40-659ffc073ec7	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
91591f15-7809-42f6-ba40-659ffc073ec7	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
91591f15-7809-42f6-ba40-659ffc073ec7	28bb5202-fba9-43d9-9145-a21f3be461fb	t
91591f15-7809-42f6-ba40-659ffc073ec7	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
91591f15-7809-42f6-ba40-659ffc073ec7	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
91591f15-7809-42f6-ba40-659ffc073ec7	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
91591f15-7809-42f6-ba40-659ffc073ec7	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
91591f15-7809-42f6-ba40-659ffc073ec7	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
b37d425e-5951-4065-b582-dbefc77d5b61	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
b37d425e-5951-4065-b582-dbefc77d5b61	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
b37d425e-5951-4065-b582-dbefc77d5b61	28bb5202-fba9-43d9-9145-a21f3be461fb	t
b37d425e-5951-4065-b582-dbefc77d5b61	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
b37d425e-5951-4065-b582-dbefc77d5b61	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
b37d425e-5951-4065-b582-dbefc77d5b61	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
b37d425e-5951-4065-b582-dbefc77d5b61	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
b37d425e-5951-4065-b582-dbefc77d5b61	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	7517764d-146d-4f29-87d4-230df456e356	t
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	28bb5202-fba9-43d9-9145-a21f3be461fb	t
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
\.


--
-- Data for Name: client_scope_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_scope_role_mapping (scope_id, role_id) FROM stdin;
b7bfac75-fe18-4683-8d2a-00c66ee86c26	c69b1cac-655d-4ae8-9f11-2cc03e00bf8e
a6377b76-d5cc-46bb-851a-be5024fdcdf6	1499c366-9f74-4260-b11b-3fcbbf1aa75e
\.


--
-- Data for Name: client_session; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_session (id, client_id, redirect_uri, state, "timestamp", session_id, auth_method, realm_id, auth_user_id, current_action) FROM stdin;
\.


--
-- Data for Name: client_session_auth_status; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_session_auth_status (authenticator, status, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_note; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_session_note (name, value, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_prot_mapper; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_session_prot_mapper (protocol_mapper_id, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_role; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_session_role (role_id, client_session) FROM stdin;
\.


--
-- Data for Name: client_user_session_note; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.client_user_session_note (name, value, client_session) FROM stdin;
\.


--
-- Data for Name: component; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.component (id, name, parent_id, provider_id, provider_type, realm_id, sub_type) FROM stdin;
fc7fe48a-0470-4762-9670-48522abda1bb	Trusted Hosts	master	trusted-hosts	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
a13d9d21-3e7c-4282-a89b-38ffb5d3b55d	Consent Required	master	consent-required	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
be1f5a66-1de5-450c-b375-6cbb20de8415	Full Scope Disabled	master	scope	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
f1b1bf1f-9431-4dcb-9587-1dcf2af4279f	Max Clients Limit	master	max-clients	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
425d4fd0-cf57-4775-9fd5-4bf80fd41d32	Allowed Protocol Mapper Types	master	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
9274645a-0842-4d02-9a5a-3c96da578173	Allowed Client Scopes	master	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	anonymous
43a26751-bf53-414d-bd07-8294bc9b4f75	Allowed Protocol Mapper Types	master	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	authenticated
525bd548-fb7b-44a8-adb8-e349c6540442	Allowed Client Scopes	master	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	master	authenticated
e2e3b32e-f06c-45cc-a135-8396ad5e1898	rsa-generated	master	rsa-generated	org.keycloak.keys.KeyProvider	master	\N
0cbc2a1e-02a2-4bb5-be29-0c9e9e1d812c	hmac-generated	master	hmac-generated	org.keycloak.keys.KeyProvider	master	\N
c98055d0-e53a-46f7-839d-3481db649881	aes-generated	master	aes-generated	org.keycloak.keys.KeyProvider	master	\N
d81f1123-8d37-479f-bd61-70a079cc9768	rsa-generated	companyon	rsa-generated	org.keycloak.keys.KeyProvider	companyon	\N
0b7346b1-bf5a-41dd-80a0-db88a3df9a1c	hmac-generated	companyon	hmac-generated	org.keycloak.keys.KeyProvider	companyon	\N
b5a56c6c-6986-44f9-a838-643820f6ced4	aes-generated	companyon	aes-generated	org.keycloak.keys.KeyProvider	companyon	\N
90a002e6-5d19-4536-8cff-221e872d271d	Trusted Hosts	companyon	trusted-hosts	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
3b93144a-82bb-4094-b254-bfcbc07b50ea	Consent Required	companyon	consent-required	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
d7e0867c-d2a1-497d-85a8-639ee1bb4fff	Full Scope Disabled	companyon	scope	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
368f5044-3901-4258-9356-95a66d8fe911	Max Clients Limit	companyon	max-clients	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
5f9da9ca-196b-494e-881f-a97b82409e4a	Allowed Protocol Mapper Types	companyon	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
71d3dbea-dc30-4b5d-a5e5-fab2452a578a	Allowed Client Scopes	companyon	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	anonymous
13b8c5c9-42b9-479c-ac33-34440e3eca79	Allowed Protocol Mapper Types	companyon	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	authenticated
917dfb70-9b10-4200-818c-c2f8b2d4d1b7	Allowed Client Scopes	companyon	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	companyon	authenticated
\.


--
-- Data for Name: component_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.component_config (id, component_id, name, value) FROM stdin;
28f4ee9c-3913-4f1a-bdec-90654afed8d0	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	saml-role-list-mapper
d4080c04-e045-4c16-b047-e1e35661795a	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	saml-user-attribute-mapper
2edb0128-bf57-4d0e-a7f9-d47b11274708	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	saml-user-property-mapper
ec4a3cc7-492d-407c-80a5-eb2f3f319f06	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	oidc-address-mapper
966e7572-0634-48b9-9b13-106d1647e6e8	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
49ce6b0c-9b24-4678-866f-1705030a0e02	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	oidc-full-name-mapper
8cb0d413-9c7e-425b-acd3-b33b7e4493b8	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
f73c8830-2954-4426-8403-57542816eca7	43a26751-bf53-414d-bd07-8294bc9b4f75	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
aa689b11-a3f3-43d2-ad41-6e69d79cf576	525bd548-fb7b-44a8-adb8-e349c6540442	allow-default-scopes	true
d8e8f1fd-57a2-4b73-9e72-e11482656802	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	oidc-address-mapper
df0e65e9-61de-48b1-9a20-e929d00b3dff	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
d26cdddc-de7e-41f5-84e6-5b5c8fef9822	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
03f98490-63db-46bb-9c81-51decfd4c7b9	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	saml-user-attribute-mapper
f1cb1bea-e04b-4769-9508-e7423d77cd74	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	saml-user-property-mapper
f8742bb7-5c45-4e8d-8e03-1db0a921edbc	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	oidc-full-name-mapper
025996f6-e007-49e5-ad50-d76c3f1e1821	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	saml-role-list-mapper
21a1063e-7a7a-48a1-b679-b6e8d20ae9a0	425d4fd0-cf57-4775-9fd5-4bf80fd41d32	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
95c9468f-b22b-4e63-a012-894eefafb61a	f1b1bf1f-9431-4dcb-9587-1dcf2af4279f	max-clients	200
93ced827-a799-40e2-8493-e2c8b4e6ab51	9274645a-0842-4d02-9a5a-3c96da578173	allow-default-scopes	true
818364b6-7778-4ca7-aec9-df0a30eb1c38	fc7fe48a-0470-4762-9670-48522abda1bb	host-sending-registration-request-must-match	true
bbb8b487-c165-456b-9f09-c5a236d52dfa	fc7fe48a-0470-4762-9670-48522abda1bb	client-uris-must-match	true
4c4639e0-f2a4-435b-a507-c2b4f44f921c	0cbc2a1e-02a2-4bb5-be29-0c9e9e1d812c	kid	e759e58f-c9d2-427e-aa8d-6b18a56c0808
3dcc74d7-2f02-4cc0-803b-d824dccaadce	0cbc2a1e-02a2-4bb5-be29-0c9e9e1d812c	secret	904tQelKxib8eg9CPKfFoB4LAX-1j6OO2q_T3HKgBNZiCPcJKm4uc2v3S3LP1jsKgXGajUbrgbuixIAAEN3mzw
813562b8-318f-425f-b4df-3389385d3692	0cbc2a1e-02a2-4bb5-be29-0c9e9e1d812c	priority	100
90c75442-e7f8-41b4-884a-6fae9ca606e6	0cbc2a1e-02a2-4bb5-be29-0c9e9e1d812c	algorithm	HS256
8c41ff20-f1ce-4213-849a-17769e4daef1	e2e3b32e-f06c-45cc-a135-8396ad5e1898	priority	100
db696ae6-7aa0-4ee9-8562-49708686432d	e2e3b32e-f06c-45cc-a135-8396ad5e1898	privateKey	MIIEowIBAAKCAQEAtorikot2cwHGkZyEQhZ9uCTcr/nifybO35OIOmWafn/TTwBoyUnTR2COJYjie6rmq7PHxVDD9aOe/XQ8j37mGrOFP3/Gzd45MiDKlypkvWdKoWRylCtZDmA7GofLLiP64c1ecrVP08aIwzk4Xej5Q0BB7dWqPx3oppNVVaqkOC5X1OfTLvgLSKOiUHlWsrndDPQGqq518MAtdaBELVjwU31AQTbg9G2nL80qtqlEtFVaOud+Vj7xaM6yNXKVnu3Mc6PAa6tWxmrhCqfYmh+IQqp+BcoV9AGZPlUGhtYxnAhintBFUXXNCgjMTF2TcGdBb0ta050zgaKqehzfEHS++QIDAQABAoIBACJm1EJn9B/CguioRaiq0nCkyc6EWnI6Hxy1kiPnSBF1B57I5fahaib2hHOAc6Z409YiIJEIIpYliu4bPNqSQM1hbchEzXO44LCYy3VnsOqdD8yQwKM59COocRsT4m/EqiBp8tXNY8j2jom0O/I+2NMaxhILhXU1rsXN2HQ3n5FSoBy03W+H0DKhz6Vj5iwlguyRgxrUE/yN+1CJd9FWgDgnVTFRNWBRDNwjhgSu5xq8BARVDb8wfayMGDxJwIoxWESxCT/4lSYBKhRnmbAn2vPN8VY+JAa03KlFb0agk+vFtq5kbjdRaCFBoxvEncYL5ofIrdLiCzn4Fnb3y7kbYrECgYEA+/SNeaovSNNrhOnCTUNcDVm3fZ5/Pe7v6Am2wl9uovBByGsVXAuaI3d6yA/4OBVUpnzNKrghGxi/XoLK5NkIUxc71+Wvo3T1j7U9Wb4/xWwv4NRixdEBZ09+LedolhrC5SQeQ86iFAr+78uxA8WFSOuinDhvr6BVRYd6+ZAPUi0CgYEAuXkSB64Wjc1Jz1wSGZSZS+pxv51qS3TpX9Z5eEtuh4jB8CoRNpzKXsfMB309NcrmcjJGziapWpeSsxi8cR8RDbQtv9YDjbUGTUJlKrD6fyvF5E2/kDUObUri20ZIuP4JBRgixO5YnYTmFDAXyrSHDMVr+x4Xuo7NVWFvFIM2e30CgYEAvfx4OcfUg+u4Za8G7s/eN8rxC+5aME9d4/B/OS5Q9NiKIiBtKM0u25OaqAUyvMeI30m4Q4A4poAQvamOaOYm2tDoNTJjMLRe/pBBxMwqtimQKM699EBX+Jj+ooqeWKGxWKvVjnffy5FHD3be8eMdUBEuuvHrddlGs3knh8vTYbkCgYA+RnWUWJLAGB7hXiPBrRBv2g9JbFyFa4L0p/nvNzysi3G65xUShkC45FMQbK/BwRfxXgpCK98PSPd75SFYxjqepFH8KKrWyOKNzG5VkEj7rEHs72IT1Iy8i7lyML44RyvWJw19nB3q/fWW77L9X/I8iolALxF+lXZCZc27UHdZrQKBgATkg6iKtlelelT/o9IpcOGH5dUsSN2hhhm086v7Kcq3M8mtLJ3iWAx2YDRwVigJMKw1uUPofCAQDjF1qO4ImaQqO3Y0sXiZbzlFGp10RusELQwGkNgX6Mmo7KaQrPl6H9le1yey6xKGJjg6cVEKq7C/Egk3FauM0YT8cTIiR2OF
dc953e92-3741-4828-9434-0fdb2e1dc914	e2e3b32e-f06c-45cc-a135-8396ad5e1898	certificate	MIICmzCCAYMCBgFzPOXPLTANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjAwNzExMDgwMTAwWhcNMzAwNzExMDgwMjQwWjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC2iuKSi3ZzAcaRnIRCFn24JNyv+eJ/Js7fk4g6ZZp+f9NPAGjJSdNHYI4liOJ7quars8fFUMP1o579dDyPfuYas4U/f8bN3jkyIMqXKmS9Z0qhZHKUK1kOYDsah8suI/rhzV5ytU/TxojDOThd6PlDQEHt1ao/Heimk1VVqqQ4LlfU59Mu+AtIo6JQeVayud0M9AaqrnXwwC11oEQtWPBTfUBBNuD0bacvzSq2qUS0VVo6535WPvFozrI1cpWe7cxzo8Brq1bGauEKp9iaH4hCqn4FyhX0AZk+VQaG1jGcCGKe0EVRdc0KCMxMXZNwZ0FvS1rTnTOBoqp6HN8QdL75AgMBAAEwDQYJKoZIhvcNAQELBQADggEBAEh7zlan1dXr8mqpD2xLKEoFNd9t2vDSiJKbG66J7nWi1SsMT4mJ+LBh7WBvsQjGCpvGnrUcpnPjn8aKMKB/O4toTayEKATCOh/Rqx/gjljTEwlWCiI7Amg7k1EWGTenCugFoNCn56VLEjAfLA9Cm/OhYgLgH1xmAVmpu41l+wHtU9OUunk2JETe9JsLeOvssU2o5J6WHcKKPmi14Ry5as1dFgD6Jrpwxhn/gS7J4u/ScvIFapyCCUJNoniAKwYd/bcwdmzlXJ8uOtVhATiZ1Jf4ZFy4cBa7QE8hfCGd6pHShL4ZX2Qk0fQP/lHH3UjiXE+jRQifmapWmwddxFzQWVc=
fdd9756e-700e-41b7-bdbb-a51c64a98a98	c98055d0-e53a-46f7-839d-3481db649881	priority	100
8b52df5a-2944-4577-b68a-6b05beb27974	c98055d0-e53a-46f7-839d-3481db649881	kid	8befd44f-59dd-407a-a3ca-5c23c99562a4
0b178b9d-b659-4df8-8a72-189d9e98e8f7	c98055d0-e53a-46f7-839d-3481db649881	secret	maiIPL8C3JgnuHGgF-CIgQ
d47d7854-180e-49a5-b009-5134047799b6	0b7346b1-bf5a-41dd-80a0-db88a3df9a1c	algorithm	HS256
d81a469d-42b1-4016-a508-06304cc027d8	0b7346b1-bf5a-41dd-80a0-db88a3df9a1c	secret	1WqMRH4DfUEDMJ-IC1Nc624ZD169uM1RHOAr9Yor7wfOvEL7vq92wbW0QHw4iHrsvlSPrLhupMqkHdpqcBx9QA
888abe6e-6309-4daa-9c94-ab148d2a418a	0b7346b1-bf5a-41dd-80a0-db88a3df9a1c	priority	100
f6abf305-4325-42d6-8e35-0527084d6e56	0b7346b1-bf5a-41dd-80a0-db88a3df9a1c	kid	f2918f54-a688-441e-b39e-1ad97b269d99
c5b38afd-7917-4acc-80b6-1fda6feff660	b5a56c6c-6986-44f9-a838-643820f6ced4	kid	59b4bd38-ca8d-45eb-964e-4bcce329799d
1bb3aa3f-7f9a-4fb0-9ac1-30a6c1e8d367	b5a56c6c-6986-44f9-a838-643820f6ced4	priority	100
2ae55fcb-632d-4f60-895c-14a60317ea45	b5a56c6c-6986-44f9-a838-643820f6ced4	secret	O7NToM9p9JyH-LhUp2l0EQ
c3b36c8f-c5da-46ff-9bcd-bd24dfad8236	d81f1123-8d37-479f-bd61-70a079cc9768	priority	100
b4a9e25d-2067-4697-9255-b3cd9dd41c6a	d81f1123-8d37-479f-bd61-70a079cc9768	privateKey	MIIEpAIBAAKCAQEAka+dGOOTSzpQZ5TBoO4Q0OySTCoKFRTKJsZtsVaHnknW5eGIGWg1hpVJ+1Oh+qe9VOlQyQOti91xi30gsEsH0Lxw7z0MdP+bni80Z1QYmAWkHX2+g2RxK5Tma6kdNz8OJ7udaI8uujfd5oMV3vv7Ld0zlgqkffbljl2L3mMcYMqtUdBSksTmDDVZ/l37EUHFDj5CuyGw8GPTG7OKJi8zGd+BCRSMNu1GYDDWIO3co26y09Wuzt1YFUZtdKlNn4eBG2LYmQm1LSNRg2a0UXP3nFesAvdCP78Oef62vielWJzkXiF/LFmNdlCHH5z2bi+focBaQ9bgoG6Se8o4hH9hhQIDAQABAoIBAQCEeigl+NP5JYb2GfxVZgaepXGHlvbiIqyMxAX9rAzY8JZdJlAHoo6B2oGS7IMwFpaBf0S59gE7XVwiFt/B0AjEVIdx1ZbdUY5mMtC5YWyX2RFay/yRKKYMlnNN1VO+6DTsBpxl90Xril/bfLpHseW/2BVPzE+2R+yJICHPUVr93AVFgs8rQ2+YUrNlxDGhbFpFSs5FQMkM9EPBoJxiPlCWY5d4UDLPPKN63p7ZMwinR/WbQgdAEeeLiNaZ5imPjsmfhZJ4kkTAo1mESeqHrBWvPgZ+gwGsvTF+5pSC+0nhtWmyAZKn7YkRaaNeqqNkPq+JQ1tdxp9F/xsn9dGYwuUlAoGBAPkR7xjC7Se/g+kYxRirx5k5o/U3/x57Ut7Z9DgyZMyImy4m4RvzpQw2OXFITQ/Jz9x/WmRGIjXyDf8Qrxf37ajBTGeep/Er87UE+19dQAyTEqG7nKi1lApVlBjKpwEKkMIy31B68P4FF2Hrr3pEarVS+6RCvBQfkoz6h2gyNk2nAoGBAJW9TMX97r9K/In6K78/I3XVWHcNEYr0MeuXysxPgjRFU9CE7STOwY4bioafr3HajMvB6BXQIgDTvqckQ9+5t/PgjuiuLHWZwSxnhGkkGMfnphbBXIgx5r1rBiGPLIMI9GcKHuHQ0C4onFDgIu5v1vdxrOfupouM32+XHylinHTzAoGAPhd8db3ShYA3EFs+d4ILhmVaPH2zOhQgerH3MjNN8bd0iDLlF6TL1pmGBHmzzGXRTfTpwenCCZTIbHfqTp1eQB3AQGzLDVKZtN0DFakXmIrpsCwnR9/Mgi8Hdo6+x4GsyN0J0vRzM8BtLdolquN3frCrTy3b2WBtlnu2WgF6jC0CgYEAkJbpMmpAhuDuuIEkixh5skefk92/qdCg/6BkSCMiVDz5cOlaYGdxiTpY6iP58tOZjwsfgyimHOxaNeUT0U3xLgIRnObKsYgVez4+IpSjhSzPOTvfkPe2mgNlOTSaQ7F65qBkL6NF0ryOLJ4xvNGr3uYakx7kHO6btpJTmc/JyjcCgYBCtUZq9B0QTRANBNk+eUY7fbXVzGQcXVMbA3YqxQ1T9O1AulwlzYI0fKs4sGhNQcrV1BUZS+UPGato62Bnbr0bRF3aevhQW+TClxzwJNPJOBt8ViEEfWMlXc3OdPNHzQrtVTdjjbCcVGORjuEns6qHiTfW4BY23cbYTY6Nwu95bQ==
4c699791-08a5-4120-a5af-2843217ddcf5	d81f1123-8d37-479f-bd61-70a079cc9768	certificate	MIICoTCCAYkCBgFzPQlSQTANBgkqhkiG9w0BAQsFADAUMRIwEAYDVQQDDAljb21wYW55b24wHhcNMjAwNzExMDgzOTQ3WhcNMzAwNzExMDg0MTI3WjAUMRIwEAYDVQQDDAljb21wYW55b24wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCRr50Y45NLOlBnlMGg7hDQ7JJMKgoVFMomxm2xVoeeSdbl4YgZaDWGlUn7U6H6p71U6VDJA62L3XGLfSCwSwfQvHDvPQx0/5ueLzRnVBiYBaQdfb6DZHErlOZrqR03Pw4nu51ojy66N93mgxXe+/st3TOWCqR99uWOXYveYxxgyq1R0FKSxOYMNVn+XfsRQcUOPkK7IbDwY9Mbs4omLzMZ34EJFIw27UZgMNYg7dyjbrLT1a7O3VgVRm10qU2fh4EbYtiZCbUtI1GDZrRRc/ecV6wC90I/vw55/ra+J6VYnOReIX8sWY12UIcfnPZuL5+hwFpD1uCgbpJ7yjiEf2GFAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAHIA6CgErlho27kUTtt72c0lvvm9/HkDn6DrbNyQoeKvxwFkCI44/1w2MUi1BGtT88y6AsguIKMGn5av9sZOIvXo7h6kuFTmU7mQW+N8CYnXFd5OeCGu6U/ceWeb8k/iyiCSlYHR9vB1st2gH2+YE8zA3sZfI8ud93g3n0z9JOrqd6W3ZrxcM3DH7vmAc3Ej54NX/S0FOLnJv1szGqpKWBlv1cSJpu0RXusIDCCMQjdRIMd7UA+0e2YKYpc7hr7XMctVF6fJ5plwem02kpm//Kusuc57Mn6RrriXNRqR+zIdHH3QWJZUbSJAptWg6/zsNcVvGFxAVhIMtk7wExB7dLI=
036d8458-e547-4ae7-b70d-9e0ee8e088a3	368f5044-3901-4258-9356-95a66d8fe911	max-clients	200
00feb9a5-7355-4013-a46b-86f440ef0ed6	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	saml-user-property-mapper
70b3d319-7504-4913-b34c-84bef833ae01	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	oidc-full-name-mapper
cef4ffb8-9799-4ccf-b2fc-a7507ede49cb	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
56662c62-6147-4e4d-8ffa-ac37f5c239ee	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
74c6d3b9-47e4-4869-8363-c40d5e686981	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	oidc-address-mapper
1958e0be-a096-4760-ad1b-83cd958b20e3	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	saml-role-list-mapper
a16ff56a-2761-4d11-b36f-113c9e1b94e6	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
366d30d9-d5de-43d3-a113-c2a0792eb0b6	5f9da9ca-196b-494e-881f-a97b82409e4a	allowed-protocol-mapper-types	saml-user-attribute-mapper
27daa025-6226-4200-8211-ce066870a31f	917dfb70-9b10-4200-818c-c2f8b2d4d1b7	allow-default-scopes	true
f6a274b1-ad37-4266-9743-9ff9d61545ff	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	saml-user-property-mapper
fa88d4eb-5dbd-411d-aac2-175a511819a7	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
e1588267-dbdf-4796-a864-0135d81b80d3	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	saml-user-attribute-mapper
a50fd145-7950-444f-9327-a1167fe5669a	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	saml-role-list-mapper
6f4893f9-8719-4052-a4bd-3e517e3abda2	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
612bdf98-272d-4a67-9a19-4bf9e1231413	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	oidc-full-name-mapper
2ae7eb33-7186-4cf5-813a-d67c3a7cf98b	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	oidc-address-mapper
75cc9b3e-c8d2-4860-ab8d-847efa80276f	13b8c5c9-42b9-479c-ac33-34440e3eca79	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
6eaa9043-c944-478c-98c6-0f6341978444	71d3dbea-dc30-4b5d-a5e5-fab2452a578a	allow-default-scopes	true
42a0f7be-d1d6-430d-b040-3d5376e6b2d8	90a002e6-5d19-4536-8cff-221e872d271d	host-sending-registration-request-must-match	true
9153e4b3-234f-4b32-8772-c3c6609cd979	90a002e6-5d19-4536-8cff-221e872d271d	client-uris-must-match	true
\.


--
-- Data for Name: composite_role; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.composite_role (composite, child_role) FROM stdin;
3e3ed1d1-afb6-477e-adc4-799b2879585b	6692da34-c5fd-4366-ba96-ff23bb34504c
3e3ed1d1-afb6-477e-adc4-799b2879585b	6c64a65d-986c-43bb-8a79-842e503af67f
3e3ed1d1-afb6-477e-adc4-799b2879585b	1136e0f5-0e70-4d25-8d4b-84685d139cea
3e3ed1d1-afb6-477e-adc4-799b2879585b	9f67744a-eb12-4911-9f15-f420c462b098
3e3ed1d1-afb6-477e-adc4-799b2879585b	290663de-1dee-4916-b954-fa7941070b9d
3e3ed1d1-afb6-477e-adc4-799b2879585b	30ae4f3f-625e-4d22-9577-3f936e590980
3e3ed1d1-afb6-477e-adc4-799b2879585b	ea07cd73-2da8-4c69-a300-5370d903d2c8
3e3ed1d1-afb6-477e-adc4-799b2879585b	24e748d2-f317-4050-9392-ae9813b92048
3e3ed1d1-afb6-477e-adc4-799b2879585b	f4ae659e-9726-40a4-9579-65064b9684fb
3e3ed1d1-afb6-477e-adc4-799b2879585b	df561c2c-e887-401c-8492-4f6566ab5a3c
3e3ed1d1-afb6-477e-adc4-799b2879585b	8c4b6f2b-efc2-4303-946e-f6dc0e632fdc
3e3ed1d1-afb6-477e-adc4-799b2879585b	ea71438d-0593-4216-8337-ef2c9d894225
3e3ed1d1-afb6-477e-adc4-799b2879585b	58eab159-e3de-4ff8-982a-835fb4dabf95
3e3ed1d1-afb6-477e-adc4-799b2879585b	f2de2ad9-a62f-499e-9274-e0f52d233d81
3e3ed1d1-afb6-477e-adc4-799b2879585b	673b3c72-05a9-4297-89df-eae36f414557
3e3ed1d1-afb6-477e-adc4-799b2879585b	9a6b3655-f36e-4c69-835d-4a3b5221193f
3e3ed1d1-afb6-477e-adc4-799b2879585b	5ef74fff-ab89-4e40-9156-f300f0ecf30e
3e3ed1d1-afb6-477e-adc4-799b2879585b	49105372-d1de-4a0e-bd97-22d7b405042b
9f67744a-eb12-4911-9f15-f420c462b098	673b3c72-05a9-4297-89df-eae36f414557
9f67744a-eb12-4911-9f15-f420c462b098	49105372-d1de-4a0e-bd97-22d7b405042b
290663de-1dee-4916-b954-fa7941070b9d	9a6b3655-f36e-4c69-835d-4a3b5221193f
027dd772-2415-4346-a5f2-3f2ed5d2424a	f57b0bff-fce7-4cfb-8515-70db92814ace
2c621aa9-0a46-48c8-8c93-1cc409718a6c	4ff56426-02d7-447c-b741-ea403f9700ad
3e3ed1d1-afb6-477e-adc4-799b2879585b	9b62df16-d36b-4707-8103-2e0c0ddf2f0f
3e3ed1d1-afb6-477e-adc4-799b2879585b	569a9736-a11d-45a7-a629-688f48c32c02
3e3ed1d1-afb6-477e-adc4-799b2879585b	1a8cbb2a-97bc-483e-9395-c72d450b83b4
3e3ed1d1-afb6-477e-adc4-799b2879585b	67491263-0341-4998-8bce-6681f9779d88
3e3ed1d1-afb6-477e-adc4-799b2879585b	cbf4a5e4-acfb-45ca-b05f-8e47913a56ea
3e3ed1d1-afb6-477e-adc4-799b2879585b	67cbfac4-b232-4c16-9f14-62183b2cd16f
3e3ed1d1-afb6-477e-adc4-799b2879585b	31dd0a04-8c5e-44a2-8b61-10ba7a40112c
3e3ed1d1-afb6-477e-adc4-799b2879585b	8c1bdfe7-06e3-47d3-a7aa-f0fd80d808b2
3e3ed1d1-afb6-477e-adc4-799b2879585b	1d9a715e-d2c9-4840-bbd5-1352ba954408
3e3ed1d1-afb6-477e-adc4-799b2879585b	399d35a9-6c7c-42ea-a1a2-0d99c1402b5e
3e3ed1d1-afb6-477e-adc4-799b2879585b	e4b9b6ce-f935-4499-8134-1748ca288a74
3e3ed1d1-afb6-477e-adc4-799b2879585b	ff928268-d52b-451e-a7f3-827d2b97528f
3e3ed1d1-afb6-477e-adc4-799b2879585b	0bdfecd1-d79b-410a-a43b-c497c01d2633
3e3ed1d1-afb6-477e-adc4-799b2879585b	5f14658f-2583-41f6-9520-0bc42404c5d8
3e3ed1d1-afb6-477e-adc4-799b2879585b	03423dec-7f90-4b7e-ad9a-781ca4cf6623
3e3ed1d1-afb6-477e-adc4-799b2879585b	f09c49a1-7aa7-42b4-9c09-e394898356a5
3e3ed1d1-afb6-477e-adc4-799b2879585b	aaf6ca62-649f-485c-82f3-4b5f3a27896c
3e3ed1d1-afb6-477e-adc4-799b2879585b	612c2b9f-2564-4182-9f55-02b442f7a237
67491263-0341-4998-8bce-6681f9779d88	03423dec-7f90-4b7e-ad9a-781ca4cf6623
67491263-0341-4998-8bce-6681f9779d88	612c2b9f-2564-4182-9f55-02b442f7a237
cbf4a5e4-acfb-45ca-b05f-8e47913a56ea	f09c49a1-7aa7-42b4-9c09-e394898356a5
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	87f1454e-e432-44b4-bf4d-382bb21cbadb
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	c32ad9a1-5309-4262-bd48-12664830cc12
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	d1b59747-3ccc-4a0d-919e-0ef1557115c5
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	53baddab-2f54-432e-93fd-5421c49ed80b
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	63fa825c-30fd-472c-9adb-0f534014a8d8
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	b5ad8e57-7b9a-436e-85a8-936f979c3a91
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	6ed05576-037c-407f-9417-c7ec27e4ea1c
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	22ea279d-93cc-40ce-8d4f-e5217d240957
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	738a1048-0bac-41de-b61a-729752f27e35
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	a849a04f-140d-4fc8-9c88-6c7d336eaa33
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	60b3b209-21f4-48d8-b9b1-e7d74beec668
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	5f328c1f-c3f0-4974-9150-0a5757d8ac5d
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	33f3eea0-dc50-4ab0-b3ea-ce77bac58b67
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	70177ccd-0646-4c98-8f41-7c51e7091e73
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	cd015335-0897-47d1-9286-14d9075af374
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	188c5b68-41f9-4a3e-8e09-68d46ef41135
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	5e05b945-274c-4bf4-a07a-8875303da275
d1b59747-3ccc-4a0d-919e-0ef1557115c5	70177ccd-0646-4c98-8f41-7c51e7091e73
d1b59747-3ccc-4a0d-919e-0ef1557115c5	5e05b945-274c-4bf4-a07a-8875303da275
53baddab-2f54-432e-93fd-5421c49ed80b	cd015335-0897-47d1-9286-14d9075af374
657edb13-4e30-46fe-ad93-e8b0663ea6eb	81ba8bdc-69fa-4368-b690-cfed0f3e63ae
930ab436-893c-46e3-9407-81a15834a0f1	c4610899-b739-4ec3-91ce-67df1ba56f15
3e3ed1d1-afb6-477e-adc4-799b2879585b	b9ddbf83-f109-4230-b38e-b4de27c5abca
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	ee0b2a7d-5b51-4c34-ba87-0efa6f69c514
\.


--
-- Data for Name: credential; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.credential (id, salt, type, user_id, created_date, user_label, secret_data, credential_data, priority) FROM stdin;
d10d5f39-d38e-4fb0-a3c7-7fc3268a67eb	\N	password	69ddc4a0-0d05-4d13-a54f-b47e23986d5f	1594454561162	\N	{"value":"3S99Jcp9ukYcoa73uoP2xHoUkkgbps5t2Z8yI1gWbVr1+zNfxFhFGlE16jzLee1rx5Ojl1LmDPQgYLoVqys31A==","salt":"LyqRRfY+vi1+QEZHKqgEtg=="}	{"hashIterations":27500,"algorithm":"pbkdf2-sha256"}	10
77db360e-1b30-4376-9608-f625c56f1d82	\N	password	1891c4b4-3f8a-4744-8688-f84c4e133bde	1594463394641	\N	{"value":"ZDKt7CgpyBWGF2JL75sA/dEOfElK3iA8uJL6Jy4gGkWvm9pI6xC3PQ25K+fKbDrz7FRA3OjVImJogE1hdkvv0A==","salt":"Zrn8MtQcdGf8OsSzPibx9Q=="}	{"hashIterations":27500,"algorithm":"pbkdf2-sha256"}	10
c60b6a54-4fc6-452f-b838-58c611397ca9	\N	password	cee9cb3c-f6da-4ba5-a510-2b811e3900fd	1594463407889	\N	{"value":"OdkqcGSLj7Rn5Sa/0ChUNL34uKwxGY7EYN/MubvZtLXkKgpYxqb7EZo5Yzk/dxuT4sGelqYTSa9bpj/I2wXoLw==","salt":"VADX1uPPr9uhy+t9UomXjg=="}	{"hashIterations":27500,"algorithm":"pbkdf2-sha256"}	10
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/jpa-changelog-1.0.0.Final.xml	2020-07-11 08:02:34.790248	1	EXECUTED	7:4e70412f24a3f382c82183742ec79317	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	3.5.4	\N	\N	4454554469
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/db2-jpa-changelog-1.0.0.Final.xml	2020-07-11 08:02:34.800179	2	MARK_RAN	7:cb16724583e9675711801c6875114f28	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	3.5.4	\N	\N	4454554469
1.1.0.Beta1	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Beta1.xml	2020-07-11 08:02:34.837862	3	EXECUTED	7:0310eb8ba07cec616460794d42ade0fa	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=CLIENT_ATTRIBUTES; createTable tableName=CLIENT_SESSION_NOTE; createTable tableName=APP_NODE_REGISTRATIONS; addColumn table...		\N	3.5.4	\N	\N	4454554469
1.1.0.Final	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Final.xml	2020-07-11 08:02:34.842124	4	EXECUTED	7:5d25857e708c3233ef4439df1f93f012	renameColumn newColumnName=EVENT_TIME, oldColumnName=TIME, tableName=EVENT_ENTITY		\N	3.5.4	\N	\N	4454554469
1.2.0.Beta1	psilva@redhat.com	META-INF/jpa-changelog-1.2.0.Beta1.xml	2020-07-11 08:02:34.923238	5	EXECUTED	7:c7a54a1041d58eb3817a4a883b4d4e84	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	3.5.4	\N	\N	4454554469
1.2.0.Beta1	psilva@redhat.com	META-INF/db2-jpa-changelog-1.2.0.Beta1.xml	2020-07-11 08:02:34.927282	6	MARK_RAN	7:2e01012df20974c1c2a605ef8afe25b7	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	3.5.4	\N	\N	4454554469
1.2.0.RC1	bburke@redhat.com	META-INF/jpa-changelog-1.2.0.CR1.xml	2020-07-11 08:02:34.999658	7	EXECUTED	7:0f08df48468428e0f30ee59a8ec01a41	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	3.5.4	\N	\N	4454554469
1.2.0.RC1	bburke@redhat.com	META-INF/db2-jpa-changelog-1.2.0.CR1.xml	2020-07-11 08:02:35.00325	8	MARK_RAN	7:a77ea2ad226b345e7d689d366f185c8c	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	3.5.4	\N	\N	4454554469
1.2.0.Final	keycloak	META-INF/jpa-changelog-1.2.0.Final.xml	2020-07-11 08:02:35.008147	9	EXECUTED	7:a3377a2059aefbf3b90ebb4c4cc8e2ab	update tableName=CLIENT; update tableName=CLIENT; update tableName=CLIENT		\N	3.5.4	\N	\N	4454554469
1.3.0	bburke@redhat.com	META-INF/jpa-changelog-1.3.0.xml	2020-07-11 08:02:35.097278	10	EXECUTED	7:04c1dbedc2aa3e9756d1a1668e003451	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=ADMI...		\N	3.5.4	\N	\N	4454554469
1.4.0	bburke@redhat.com	META-INF/jpa-changelog-1.4.0.xml	2020-07-11 08:02:35.140121	11	EXECUTED	7:36ef39ed560ad07062d956db861042ba	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	3.5.4	\N	\N	4454554469
1.4.0	bburke@redhat.com	META-INF/db2-jpa-changelog-1.4.0.xml	2020-07-11 08:02:35.142838	12	MARK_RAN	7:d909180b2530479a716d3f9c9eaea3d7	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	3.5.4	\N	\N	4454554469
1.5.0	bburke@redhat.com	META-INF/jpa-changelog-1.5.0.xml	2020-07-11 08:02:35.159146	13	EXECUTED	7:cf12b04b79bea5152f165eb41f3955f6	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	3.5.4	\N	\N	4454554469
1.6.1_from15	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2020-07-11 08:02:35.176268	14	EXECUTED	7:7e32c8f05c755e8675764e7d5f514509	addColumn tableName=REALM; addColumn tableName=KEYCLOAK_ROLE; addColumn tableName=CLIENT; createTable tableName=OFFLINE_USER_SESSION; createTable tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_US_SES_PK2, tableName=...		\N	3.5.4	\N	\N	4454554469
1.6.1_from16-pre	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2020-07-11 08:02:35.177973	15	MARK_RAN	7:980ba23cc0ec39cab731ce903dd01291	delete tableName=OFFLINE_CLIENT_SESSION; delete tableName=OFFLINE_USER_SESSION		\N	3.5.4	\N	\N	4454554469
1.6.1_from16	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2020-07-11 08:02:35.179494	16	MARK_RAN	7:2fa220758991285312eb84f3b4ff5336	dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_US_SES_PK, tableName=OFFLINE_USER_SESSION; dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_CL_SES_PK, tableName=OFFLINE_CLIENT_SESSION; addColumn tableName=OFFLINE_USER_SESSION; update tableName=OF...		\N	3.5.4	\N	\N	4454554469
1.6.1	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2020-07-11 08:02:35.181156	17	EXECUTED	7:d41d8cd98f00b204e9800998ecf8427e	empty		\N	3.5.4	\N	\N	4454554469
1.7.0	bburke@redhat.com	META-INF/jpa-changelog-1.7.0.xml	2020-07-11 08:02:35.215033	18	EXECUTED	7:91ace540896df890cc00a0490ee52bbc	createTable tableName=KEYCLOAK_GROUP; createTable tableName=GROUP_ROLE_MAPPING; createTable tableName=GROUP_ATTRIBUTE; createTable tableName=USER_GROUP_MEMBERSHIP; createTable tableName=REALM_DEFAULT_GROUPS; addColumn tableName=IDENTITY_PROVIDER; ...		\N	3.5.4	\N	\N	4454554469
1.8.0	mposolda@redhat.com	META-INF/jpa-changelog-1.8.0.xml	2020-07-11 08:02:35.248345	19	EXECUTED	7:c31d1646dfa2618a9335c00e07f89f24	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	3.5.4	\N	\N	4454554469
1.8.0-2	keycloak	META-INF/jpa-changelog-1.8.0.xml	2020-07-11 08:02:35.25256	20	EXECUTED	7:df8bc21027a4f7cbbb01f6344e89ce07	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	3.5.4	\N	\N	4454554469
authz-3.4.0.CR1-resource-server-pk-change-part1	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2020-07-11 08:02:35.636224	45	EXECUTED	7:6a48ce645a3525488a90fbf76adf3bb3	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_RESOURCE; addColumn tableName=RESOURCE_SERVER_SCOPE		\N	3.5.4	\N	\N	4454554469
1.8.0	mposolda@redhat.com	META-INF/db2-jpa-changelog-1.8.0.xml	2020-07-11 08:02:35.254904	21	MARK_RAN	7:f987971fe6b37d963bc95fee2b27f8df	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	3.5.4	\N	\N	4454554469
1.8.0-2	keycloak	META-INF/db2-jpa-changelog-1.8.0.xml	2020-07-11 08:02:35.257482	22	MARK_RAN	7:df8bc21027a4f7cbbb01f6344e89ce07	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	3.5.4	\N	\N	4454554469
1.9.0	mposolda@redhat.com	META-INF/jpa-changelog-1.9.0.xml	2020-07-11 08:02:35.276627	23	EXECUTED	7:ed2dc7f799d19ac452cbcda56c929e47	update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=REALM; update tableName=REALM; customChange; dr...		\N	3.5.4	\N	\N	4454554469
1.9.1	keycloak	META-INF/jpa-changelog-1.9.1.xml	2020-07-11 08:02:35.281487	24	EXECUTED	7:80b5db88a5dda36ece5f235be8757615	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=PUBLIC_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	3.5.4	\N	\N	4454554469
1.9.1	keycloak	META-INF/db2-jpa-changelog-1.9.1.xml	2020-07-11 08:02:35.283469	25	MARK_RAN	7:1437310ed1305a9b93f8848f301726ce	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	3.5.4	\N	\N	4454554469
1.9.2	keycloak	META-INF/jpa-changelog-1.9.2.xml	2020-07-11 08:02:35.309126	26	EXECUTED	7:b82ffb34850fa0836be16deefc6a87c4	createIndex indexName=IDX_USER_EMAIL, tableName=USER_ENTITY; createIndex indexName=IDX_USER_ROLE_MAPPING, tableName=USER_ROLE_MAPPING; createIndex indexName=IDX_USER_GROUP_MAPPING, tableName=USER_GROUP_MEMBERSHIP; createIndex indexName=IDX_USER_CO...		\N	3.5.4	\N	\N	4454554469
authz-2.0.0	psilva@redhat.com	META-INF/jpa-changelog-authz-2.0.0.xml	2020-07-11 08:02:35.368545	27	EXECUTED	7:9cc98082921330d8d9266decdd4bd658	createTable tableName=RESOURCE_SERVER; addPrimaryKey constraintName=CONSTRAINT_FARS, tableName=RESOURCE_SERVER; addUniqueConstraint constraintName=UK_AU8TT6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER; createTable tableName=RESOURCE_SERVER_RESOU...		\N	3.5.4	\N	\N	4454554469
authz-2.5.1	psilva@redhat.com	META-INF/jpa-changelog-authz-2.5.1.xml	2020-07-11 08:02:35.37218	28	EXECUTED	7:03d64aeed9cb52b969bd30a7ac0db57e	update tableName=RESOURCE_SERVER_POLICY		\N	3.5.4	\N	\N	4454554469
2.1.0-KEYCLOAK-5461	bburke@redhat.com	META-INF/jpa-changelog-2.1.0.xml	2020-07-11 08:02:35.434755	29	EXECUTED	7:f1f9fd8710399d725b780f463c6b21cd	createTable tableName=BROKER_LINK; createTable tableName=FED_USER_ATTRIBUTE; createTable tableName=FED_USER_CONSENT; createTable tableName=FED_USER_CONSENT_ROLE; createTable tableName=FED_USER_CONSENT_PROT_MAPPER; createTable tableName=FED_USER_CR...		\N	3.5.4	\N	\N	4454554469
2.2.0	bburke@redhat.com	META-INF/jpa-changelog-2.2.0.xml	2020-07-11 08:02:35.447917	30	EXECUTED	7:53188c3eb1107546e6f765835705b6c1	addColumn tableName=ADMIN_EVENT_ENTITY; createTable tableName=CREDENTIAL_ATTRIBUTE; createTable tableName=FED_CREDENTIAL_ATTRIBUTE; modifyDataType columnName=VALUE, tableName=CREDENTIAL; addForeignKeyConstraint baseTableName=FED_CREDENTIAL_ATTRIBU...		\N	3.5.4	\N	\N	4454554469
2.3.0	bburke@redhat.com	META-INF/jpa-changelog-2.3.0.xml	2020-07-11 08:02:35.464271	31	EXECUTED	7:d6e6f3bc57a0c5586737d1351725d4d4	createTable tableName=FEDERATED_USER; addPrimaryKey constraintName=CONSTR_FEDERATED_USER, tableName=FEDERATED_USER; dropDefaultValue columnName=TOTP, tableName=USER_ENTITY; dropColumn columnName=TOTP, tableName=USER_ENTITY; addColumn tableName=IDE...		\N	3.5.4	\N	\N	4454554469
2.4.0	bburke@redhat.com	META-INF/jpa-changelog-2.4.0.xml	2020-07-11 08:02:35.468545	32	EXECUTED	7:454d604fbd755d9df3fd9c6329043aa5	customChange		\N	3.5.4	\N	\N	4454554469
2.5.0	bburke@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2020-07-11 08:02:35.474142	33	EXECUTED	7:57e98a3077e29caf562f7dbf80c72600	customChange; modifyDataType columnName=USER_ID, tableName=OFFLINE_USER_SESSION		\N	3.5.4	\N	\N	4454554469
2.5.0-unicode-oracle	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2020-07-11 08:02:35.476546	34	MARK_RAN	7:e4c7e8f2256210aee71ddc42f538b57a	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	3.5.4	\N	\N	4454554469
2.5.0-unicode-other-dbs	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2020-07-11 08:02:35.502538	35	EXECUTED	7:09a43c97e49bc626460480aa1379b522	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	3.5.4	\N	\N	4454554469
2.5.0-duplicate-email-support	slawomir@dabek.name	META-INF/jpa-changelog-2.5.0.xml	2020-07-11 08:02:35.506819	36	EXECUTED	7:26bfc7c74fefa9126f2ce702fb775553	addColumn tableName=REALM		\N	3.5.4	\N	\N	4454554469
2.5.0-unique-group-names	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2020-07-11 08:02:35.51209	37	EXECUTED	7:a161e2ae671a9020fff61e996a207377	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	3.5.4	\N	\N	4454554469
2.5.1	bburke@redhat.com	META-INF/jpa-changelog-2.5.1.xml	2020-07-11 08:02:35.515021	38	EXECUTED	7:37fc1781855ac5388c494f1442b3f717	addColumn tableName=FED_USER_CONSENT		\N	3.5.4	\N	\N	4454554469
3.0.0	bburke@redhat.com	META-INF/jpa-changelog-3.0.0.xml	2020-07-11 08:02:35.517805	39	EXECUTED	7:13a27db0dae6049541136adad7261d27	addColumn tableName=IDENTITY_PROVIDER		\N	3.5.4	\N	\N	4454554469
3.2.0-fix	keycloak	META-INF/jpa-changelog-3.2.0.xml	2020-07-11 08:02:35.519374	40	MARK_RAN	7:550300617e3b59e8af3a6294df8248a3	addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS		\N	3.5.4	\N	\N	4454554469
3.2.0-fix-with-keycloak-5416	keycloak	META-INF/jpa-changelog-3.2.0.xml	2020-07-11 08:02:35.52106	41	MARK_RAN	7:e3a9482b8931481dc2772a5c07c44f17	dropIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS; addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS; createIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS		\N	3.5.4	\N	\N	4454554469
3.2.0-fix-offline-sessions	hmlnarik	META-INF/jpa-changelog-3.2.0.xml	2020-07-11 08:02:35.527543	42	EXECUTED	7:72b07d85a2677cb257edb02b408f332d	customChange		\N	3.5.4	\N	\N	4454554469
3.2.0-fixed	keycloak	META-INF/jpa-changelog-3.2.0.xml	2020-07-11 08:02:35.628973	43	EXECUTED	7:a72a7858967bd414835d19e04d880312	addColumn tableName=REALM; dropPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_PK2, tableName=OFFLINE_CLIENT_SESSION; dropColumn columnName=CLIENT_SESSION_ID, tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_P...		\N	3.5.4	\N	\N	4454554469
3.3.0	keycloak	META-INF/jpa-changelog-3.3.0.xml	2020-07-11 08:02:35.632585	44	EXECUTED	7:94edff7cf9ce179e7e85f0cd78a3cf2c	addColumn tableName=USER_ENTITY		\N	3.5.4	\N	\N	4454554469
authz-3.4.0.CR1-resource-server-pk-change-part2-KEYCLOAK-6095	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2020-07-11 08:02:35.640229	46	EXECUTED	7:e64b5dcea7db06077c6e57d3b9e5ca14	customChange		\N	3.5.4	\N	\N	4454554469
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2020-07-11 08:02:35.642	47	MARK_RAN	7:fd8cf02498f8b1e72496a20afc75178c	dropIndex indexName=IDX_RES_SERV_POL_RES_SERV, tableName=RESOURCE_SERVER_POLICY; dropIndex indexName=IDX_RES_SRV_RES_RES_SRV, tableName=RESOURCE_SERVER_RESOURCE; dropIndex indexName=IDX_RES_SRV_SCOPE_RES_SRV, tableName=RESOURCE_SERVER_SCOPE		\N	3.5.4	\N	\N	4454554469
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed-nodropindex	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2020-07-11 08:02:35.669691	48	EXECUTED	7:542794f25aa2b1fbabb7e577d6646319	addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_POLICY; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_RESOURCE; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, ...		\N	3.5.4	\N	\N	4454554469
authn-3.4.0.CR1-refresh-token-max-reuse	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2020-07-11 08:02:35.672837	49	EXECUTED	7:edad604c882df12f74941dac3cc6d650	addColumn tableName=REALM		\N	3.5.4	\N	\N	4454554469
3.4.0	keycloak	META-INF/jpa-changelog-3.4.0.xml	2020-07-11 08:02:35.70818	50	EXECUTED	7:0f88b78b7b46480eb92690cbf5e44900	addPrimaryKey constraintName=CONSTRAINT_REALM_DEFAULT_ROLES, tableName=REALM_DEFAULT_ROLES; addPrimaryKey constraintName=CONSTRAINT_COMPOSITE_ROLE, tableName=COMPOSITE_ROLE; addPrimaryKey constraintName=CONSTR_REALM_DEFAULT_GROUPS, tableName=REALM...		\N	3.5.4	\N	\N	4454554469
3.4.0-KEYCLOAK-5230	hmlnarik@redhat.com	META-INF/jpa-changelog-3.4.0.xml	2020-07-11 08:02:35.743295	51	EXECUTED	7:d560e43982611d936457c327f872dd59	createIndex indexName=IDX_FU_ATTRIBUTE, tableName=FED_USER_ATTRIBUTE; createIndex indexName=IDX_FU_CONSENT, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CONSENT_RU, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CREDENTIAL, t...		\N	3.5.4	\N	\N	4454554469
3.4.1	psilva@redhat.com	META-INF/jpa-changelog-3.4.1.xml	2020-07-11 08:02:35.74801	52	EXECUTED	7:c155566c42b4d14ef07059ec3b3bbd8e	modifyDataType columnName=VALUE, tableName=CLIENT_ATTRIBUTES		\N	3.5.4	\N	\N	4454554469
3.4.2	keycloak	META-INF/jpa-changelog-3.4.2.xml	2020-07-11 08:02:35.752313	53	EXECUTED	7:b40376581f12d70f3c89ba8ddf5b7dea	update tableName=REALM		\N	3.5.4	\N	\N	4454554469
3.4.2-KEYCLOAK-5172	mkanis@redhat.com	META-INF/jpa-changelog-3.4.2.xml	2020-07-11 08:02:35.756252	54	EXECUTED	7:a1132cc395f7b95b3646146c2e38f168	update tableName=CLIENT		\N	3.5.4	\N	\N	4454554469
4.0.0-KEYCLOAK-6335	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2020-07-11 08:02:35.765853	55	EXECUTED	7:d8dc5d89c789105cfa7ca0e82cba60af	createTable tableName=CLIENT_AUTH_FLOW_BINDINGS; addPrimaryKey constraintName=C_CLI_FLOW_BIND, tableName=CLIENT_AUTH_FLOW_BINDINGS		\N	3.5.4	\N	\N	4454554469
4.0.0-CLEANUP-UNUSED-TABLE	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2020-07-11 08:02:35.771996	56	EXECUTED	7:7822e0165097182e8f653c35517656a3	dropTable tableName=CLIENT_IDENTITY_PROV_MAPPING		\N	3.5.4	\N	\N	4454554469
4.0.0-KEYCLOAK-6228	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2020-07-11 08:02:35.802396	57	EXECUTED	7:c6538c29b9c9a08f9e9ea2de5c2b6375	dropUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHOGM8UEWRT, tableName=USER_CONSENT; dropNotNullConstraint columnName=CLIENT_ID, tableName=USER_CONSENT; addColumn tableName=USER_CONSENT; addUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHO...		\N	3.5.4	\N	\N	4454554469
4.0.0-KEYCLOAK-5579-fixed	mposolda@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2020-07-11 08:02:35.903012	58	EXECUTED	7:6d4893e36de22369cf73bcb051ded875	dropForeignKeyConstraint baseTableName=CLIENT_TEMPLATE_ATTRIBUTES, constraintName=FK_CL_TEMPL_ATTR_TEMPL; renameTable newTableName=CLIENT_SCOPE_ATTRIBUTES, oldTableName=CLIENT_TEMPLATE_ATTRIBUTES; renameColumn newColumnName=SCOPE_ID, oldColumnName...		\N	3.5.4	\N	\N	4454554469
authz-4.0.0.CR1	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.CR1.xml	2020-07-11 08:02:35.923229	59	EXECUTED	7:57960fc0b0f0dd0563ea6f8b2e4a1707	createTable tableName=RESOURCE_SERVER_PERM_TICKET; addPrimaryKey constraintName=CONSTRAINT_FAPMT, tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRHO213XCX4WNKOG82SSPMT...		\N	3.5.4	\N	\N	4454554469
authz-4.0.0.Beta3	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.Beta3.xml	2020-07-11 08:02:35.927401	60	EXECUTED	7:2b4b8bff39944c7097977cc18dbceb3b	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRPO2128CX4WNKOG82SSRFY, referencedTableName=RESOURCE_SERVER_POLICY		\N	3.5.4	\N	\N	4454554469
authz-4.2.0.Final	mhajas@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2020-07-11 08:02:35.933947	61	EXECUTED	7:2aa42a964c59cd5b8ca9822340ba33a8	createTable tableName=RESOURCE_URIS; addForeignKeyConstraint baseTableName=RESOURCE_URIS, constraintName=FK_RESOURCE_SERVER_URIS, referencedTableName=RESOURCE_SERVER_RESOURCE; customChange; dropColumn columnName=URI, tableName=RESOURCE_SERVER_RESO...		\N	3.5.4	\N	\N	4454554469
authz-4.2.0.Final-KEYCLOAK-9944	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2020-07-11 08:02:35.940196	62	EXECUTED	7:9ac9e58545479929ba23f4a3087a0346	addPrimaryKey constraintName=CONSTRAINT_RESOUR_URIS_PK, tableName=RESOURCE_URIS		\N	3.5.4	\N	\N	4454554469
4.2.0-KEYCLOAK-6313	wadahiro@gmail.com	META-INF/jpa-changelog-4.2.0.xml	2020-07-11 08:02:35.943222	63	EXECUTED	7:14d407c35bc4fe1976867756bcea0c36	addColumn tableName=REQUIRED_ACTION_PROVIDER		\N	3.5.4	\N	\N	4454554469
4.3.0-KEYCLOAK-7984	wadahiro@gmail.com	META-INF/jpa-changelog-4.3.0.xml	2020-07-11 08:02:35.945872	64	EXECUTED	7:241a8030c748c8548e346adee548fa93	update tableName=REQUIRED_ACTION_PROVIDER		\N	3.5.4	\N	\N	4454554469
4.6.0-KEYCLOAK-7950	psilva@redhat.com	META-INF/jpa-changelog-4.6.0.xml	2020-07-11 08:02:35.948371	65	EXECUTED	7:7d3182f65a34fcc61e8d23def037dc3f	update tableName=RESOURCE_SERVER_RESOURCE		\N	3.5.4	\N	\N	4454554469
4.6.0-KEYCLOAK-8377	keycloak	META-INF/jpa-changelog-4.6.0.xml	2020-07-11 08:02:35.958836	66	EXECUTED	7:b30039e00a0b9715d430d1b0636728fa	createTable tableName=ROLE_ATTRIBUTE; addPrimaryKey constraintName=CONSTRAINT_ROLE_ATTRIBUTE_PK, tableName=ROLE_ATTRIBUTE; addForeignKeyConstraint baseTableName=ROLE_ATTRIBUTE, constraintName=FK_ROLE_ATTRIBUTE_ID, referencedTableName=KEYCLOAK_ROLE...		\N	3.5.4	\N	\N	4454554469
4.6.0-KEYCLOAK-8555	gideonray@gmail.com	META-INF/jpa-changelog-4.6.0.xml	2020-07-11 08:02:35.962952	67	EXECUTED	7:3797315ca61d531780f8e6f82f258159	createIndex indexName=IDX_COMPONENT_PROVIDER_TYPE, tableName=COMPONENT		\N	3.5.4	\N	\N	4454554469
4.7.0-KEYCLOAK-1267	sguilhen@redhat.com	META-INF/jpa-changelog-4.7.0.xml	2020-07-11 08:02:35.966046	68	EXECUTED	7:c7aa4c8d9573500c2d347c1941ff0301	addColumn tableName=REALM		\N	3.5.4	\N	\N	4454554469
4.7.0-KEYCLOAK-7275	keycloak	META-INF/jpa-changelog-4.7.0.xml	2020-07-11 08:02:35.97409	69	EXECUTED	7:b207faee394fc074a442ecd42185a5dd	renameColumn newColumnName=CREATED_ON, oldColumnName=LAST_SESSION_REFRESH, tableName=OFFLINE_USER_SESSION; addNotNullConstraint columnName=CREATED_ON, tableName=OFFLINE_USER_SESSION; addColumn tableName=OFFLINE_USER_SESSION; customChange; createIn...		\N	3.5.4	\N	\N	4454554469
4.8.0-KEYCLOAK-8835	sguilhen@redhat.com	META-INF/jpa-changelog-4.8.0.xml	2020-07-11 08:02:35.978274	70	EXECUTED	7:ab9a9762faaba4ddfa35514b212c4922	addNotNullConstraint columnName=SSO_MAX_LIFESPAN_REMEMBER_ME, tableName=REALM; addNotNullConstraint columnName=SSO_IDLE_TIMEOUT_REMEMBER_ME, tableName=REALM		\N	3.5.4	\N	\N	4454554469
authz-7.0.0-KEYCLOAK-10443	psilva@redhat.com	META-INF/jpa-changelog-authz-7.0.0.xml	2020-07-11 08:02:35.98114	71	EXECUTED	7:b9710f74515a6ccb51b72dc0d19df8c4	addColumn tableName=RESOURCE_SERVER		\N	3.5.4	\N	\N	4454554469
8.0.0-adding-credential-columns	keycloak	META-INF/jpa-changelog-8.0.0.xml	2020-07-11 08:02:35.986129	72	EXECUTED	7:ec9707ae4d4f0b7452fee20128083879	addColumn tableName=CREDENTIAL; addColumn tableName=FED_USER_CREDENTIAL		\N	3.5.4	\N	\N	4454554469
8.0.0-updating-credential-data-not-oracle	keycloak	META-INF/jpa-changelog-8.0.0.xml	2020-07-11 08:02:35.991007	73	EXECUTED	7:03b3f4b264c3c68ba082250a80b74216	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	3.5.4	\N	\N	4454554469
8.0.0-updating-credential-data-oracle	keycloak	META-INF/jpa-changelog-8.0.0.xml	2020-07-11 08:02:35.992455	74	MARK_RAN	7:64c5728f5ca1f5aa4392217701c4fe23	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	3.5.4	\N	\N	4454554469
8.0.0-credential-cleanup-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2020-07-11 08:02:36.005803	75	EXECUTED	7:b48da8c11a3d83ddd6b7d0c8c2219345	dropDefaultValue columnName=COUNTER, tableName=CREDENTIAL; dropDefaultValue columnName=DIGITS, tableName=CREDENTIAL; dropDefaultValue columnName=PERIOD, tableName=CREDENTIAL; dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; dropColumn ...		\N	3.5.4	\N	\N	4454554469
8.0.0-resource-tag-support	keycloak	META-INF/jpa-changelog-8.0.0.xml	2020-07-11 08:02:36.012323	76	EXECUTED	7:a73379915c23bfad3e8f5c6d5c0aa4bd	addColumn tableName=MIGRATION_MODEL; createIndex indexName=IDX_UPDATE_TIME, tableName=MIGRATION_MODEL		\N	3.5.4	\N	\N	4454554469
9.0.0-always-display-client	keycloak	META-INF/jpa-changelog-9.0.0.xml	2020-07-11 08:02:36.015272	77	EXECUTED	7:39e0073779aba192646291aa2332493d	addColumn tableName=CLIENT		\N	3.5.4	\N	\N	4454554469
9.0.0-drop-constraints-for-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2020-07-11 08:02:36.016965	78	MARK_RAN	7:81f87368f00450799b4bf42ea0b3ec34	dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5PMT, tableName=RESOURCE_SERVER_PERM_TICKET; dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER_RESOURCE; dropPrimaryKey constraintName=CONSTRAINT_O...		\N	3.5.4	\N	\N	4454554469
9.0.0-increase-column-size-federated-fk	keycloak	META-INF/jpa-changelog-9.0.0.xml	2020-07-11 08:02:36.029825	79	EXECUTED	7:20b37422abb9fb6571c618148f013a15	modifyDataType columnName=CLIENT_ID, tableName=FED_USER_CONSENT; modifyDataType columnName=CLIENT_REALM_CONSTRAINT, tableName=KEYCLOAK_ROLE; modifyDataType columnName=OWNER, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=CLIENT_ID, ta...		\N	3.5.4	\N	\N	4454554469
9.0.0-recreate-constraints-after-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2020-07-11 08:02:36.031758	80	MARK_RAN	7:1970bb6cfb5ee800736b95ad3fb3c78a	addNotNullConstraint columnName=CLIENT_ID, tableName=OFFLINE_CLIENT_SESSION; addNotNullConstraint columnName=OWNER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNullConstraint columnName=REQUESTER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNull...		\N	3.5.4	\N	\N	4454554469
9.0.1-add-index-to-client.client_id	keycloak	META-INF/jpa-changelog-9.0.1.xml	2020-07-11 08:02:36.036799	81	EXECUTED	7:45d9b25fc3b455d522d8dcc10a0f4c80	createIndex indexName=IDX_CLIENT_ID, tableName=CLIENT		\N	3.5.4	\N	\N	4454554469
9.0.1-KEYCLOAK-12579-drop-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2020-07-11 08:02:36.038966	82	MARK_RAN	7:890ae73712bc187a66c2813a724d037f	dropUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	3.5.4	\N	\N	4454554469
9.0.1-KEYCLOAK-12579-add-not-null-constraint	keycloak	META-INF/jpa-changelog-9.0.1.xml	2020-07-11 08:02:36.042341	83	EXECUTED	7:0a211980d27fafe3ff50d19a3a29b538	addNotNullConstraint columnName=PARENT_GROUP, tableName=KEYCLOAK_GROUP		\N	3.5.4	\N	\N	4454554469
9.0.1-KEYCLOAK-12579-recreate-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2020-07-11 08:02:36.044228	84	MARK_RAN	7:a161e2ae671a9020fff61e996a207377	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	3.5.4	\N	\N	4454554469
9.0.1-add-index-to-events	keycloak	META-INF/jpa-changelog-9.0.1.xml	2020-07-11 08:02:36.049016	85	EXECUTED	7:01c49302201bdf815b0a18d1f98a55dc	createIndex indexName=IDX_EVENT_TIME, tableName=EVENT_ENTITY		\N	3.5.4	\N	\N	4454554469
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
1000	f	\N	\N
1001	f	\N	\N
\.


--
-- Data for Name: default_client_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.default_client_scope (realm_id, scope_id, default_scope) FROM stdin;
master	b7bfac75-fe18-4683-8d2a-00c66ee86c26	f
master	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9	t
master	b3b85baf-867f-4ae1-ae51-b4e1037c49f3	t
master	986138f6-4287-4215-ae66-bebda186a7d5	t
master	2816a579-86b3-4afb-ba5e-f6bdf0339b9d	f
master	0873f689-ea5f-4b35-990f-9f08976f9fe7	f
master	5dae4cca-5ac3-4e56-ac42-29244a3e560a	t
master	f4b7809a-43ce-4884-ab68-fc88aacde2f0	t
master	0aae6106-7ce2-49a9-b359-eea980b01322	f
companyon	a6377b76-d5cc-46bb-851a-be5024fdcdf6	f
companyon	7517764d-146d-4f29-87d4-230df456e356	t
companyon	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1	t
companyon	b50a942b-6668-4f80-a7ca-83ae6e3a959a	t
companyon	65c63377-ca9b-4724-aea4-e37022dd4fc9	f
companyon	104ef280-4d18-4d17-87a0-c1c71edea2a3	f
companyon	28bb5202-fba9-43d9-9145-a21f3be461fb	t
companyon	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa	t
companyon	1e1e360f-b93d-49e8-b936-17e4da3cc021	f
\.


--
-- Data for Name: event_entity; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.event_entity (id, client_id, details_json, error, ip_address, realm_id, session_id, event_time, type, user_id) FROM stdin;
\.


--
-- Data for Name: fed_user_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_attribute (id, name, user_id, realm_id, storage_provider_id, value) FROM stdin;
\.


--
-- Data for Name: fed_user_consent; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_consent (id, client_id, user_id, realm_id, storage_provider_id, created_date, last_updated_date, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: fed_user_consent_cl_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_consent_cl_scope (user_consent_id, scope_id) FROM stdin;
\.


--
-- Data for Name: fed_user_credential; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_credential (id, salt, type, created_date, user_id, realm_id, storage_provider_id, user_label, secret_data, credential_data, priority) FROM stdin;
\.


--
-- Data for Name: fed_user_group_membership; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_group_membership (group_id, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: fed_user_required_action; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_required_action (required_action, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: fed_user_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.fed_user_role_mapping (role_id, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: federated_identity; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.federated_identity (identity_provider, realm_id, federated_user_id, federated_username, token, user_id) FROM stdin;
\.


--
-- Data for Name: federated_user; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.federated_user (id, storage_provider_id, realm_id) FROM stdin;
\.


--
-- Data for Name: group_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.group_attribute (id, name, value, group_id) FROM stdin;
\.


--
-- Data for Name: group_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.group_role_mapping (role_id, group_id) FROM stdin;
11d4b42d-27ed-4636-bbb7-e1fad780be10	b640082c-f2d1-4d00-8746-d30a27a44013
2b4d17bb-c8c6-4982-be42-6e7f9b413d68	b640082c-f2d1-4d00-8746-d30a27a44013
2b4d17bb-c8c6-4982-be42-6e7f9b413d68	92faae8d-5622-4929-b504-968fc7a7ec65
\.


--
-- Data for Name: identity_provider; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.identity_provider (internal_id, enabled, provider_alias, provider_id, store_token, authenticate_by_default, realm_id, add_token_role, trust_email, first_broker_login_flow_id, post_broker_login_flow_id, provider_display_name, link_only) FROM stdin;
\.


--
-- Data for Name: identity_provider_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.identity_provider_config (identity_provider_id, value, name) FROM stdin;
\.


--
-- Data for Name: identity_provider_mapper; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.identity_provider_mapper (id, name, idp_alias, idp_mapper_name, realm_id) FROM stdin;
\.


--
-- Data for Name: idp_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.idp_mapper_config (idp_mapper_id, value, name) FROM stdin;
\.


--
-- Data for Name: keycloak_group; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.keycloak_group (id, name, parent_group, realm_id) FROM stdin;
b640082c-f2d1-4d00-8746-d30a27a44013	admin	 	companyon
92faae8d-5622-4929-b504-968fc7a7ec65	user	 	companyon
\.


--
-- Data for Name: keycloak_role; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.keycloak_role (id, client_realm_constraint, client_role, description, name, realm_id, client, realm) FROM stdin;
3e3ed1d1-afb6-477e-adc4-799b2879585b	master	f	${role_admin}	admin	master	\N	master
6692da34-c5fd-4366-ba96-ff23bb34504c	master	f	${role_create-realm}	create-realm	master	\N	master
6c64a65d-986c-43bb-8a79-842e503af67f	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_create-client}	create-client	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
1136e0f5-0e70-4d25-8d4b-84685d139cea	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-realm}	view-realm	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
9f67744a-eb12-4911-9f15-f420c462b098	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-users}	view-users	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
290663de-1dee-4916-b954-fa7941070b9d	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-clients}	view-clients	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
30ae4f3f-625e-4d22-9577-3f936e590980	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-events}	view-events	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
ea07cd73-2da8-4c69-a300-5370d903d2c8	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-identity-providers}	view-identity-providers	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
24e748d2-f317-4050-9392-ae9813b92048	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_view-authorization}	view-authorization	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
f4ae659e-9726-40a4-9579-65064b9684fb	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-realm}	manage-realm	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
df561c2c-e887-401c-8492-4f6566ab5a3c	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-users}	manage-users	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
8c4b6f2b-efc2-4303-946e-f6dc0e632fdc	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-clients}	manage-clients	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
ea71438d-0593-4216-8337-ef2c9d894225	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-events}	manage-events	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
58eab159-e3de-4ff8-982a-835fb4dabf95	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-identity-providers}	manage-identity-providers	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
f2de2ad9-a62f-499e-9274-e0f52d233d81	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_manage-authorization}	manage-authorization	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
673b3c72-05a9-4297-89df-eae36f414557	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_query-users}	query-users	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
9a6b3655-f36e-4c69-835d-4a3b5221193f	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_query-clients}	query-clients	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
5ef74fff-ab89-4e40-9156-f300f0ecf30e	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_query-realms}	query-realms	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
49105372-d1de-4a0e-bd97-22d7b405042b	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_query-groups}	query-groups	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
524c815d-7089-4723-be67-fad4e743e865	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_view-profile}	view-profile	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
027dd772-2415-4346-a5f2-3f2ed5d2424a	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_manage-account}	manage-account	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
f57b0bff-fce7-4cfb-8515-70db92814ace	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_manage-account-links}	manage-account-links	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
d2ec3ff3-6fcc-41e6-99f2-df7c53654287	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_view-applications}	view-applications	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
4ff56426-02d7-447c-b741-ea403f9700ad	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_view-consent}	view-consent	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
2c621aa9-0a46-48c8-8c93-1cc409718a6c	25761e29-96d5-4661-a01e-744abbe404b2	t	${role_manage-consent}	manage-consent	master	25761e29-96d5-4661-a01e-744abbe404b2	\N
e11ac9b0-00c6-442c-bfc6-616809f76be6	e6bbda91-de2b-4957-912e-2dfa7dc9a69e	t	${role_read-token}	read-token	master	e6bbda91-de2b-4957-912e-2dfa7dc9a69e	\N
9b62df16-d36b-4707-8103-2e0c0ddf2f0f	4acfb22d-b9a5-4cf7-b885-c2570bae9106	t	${role_impersonation}	impersonation	master	4acfb22d-b9a5-4cf7-b885-c2570bae9106	\N
c69b1cac-655d-4ae8-9f11-2cc03e00bf8e	master	f	${role_offline-access}	offline_access	master	\N	master
a5eaee59-8ec0-4c56-a10d-02d4222d840e	master	f	${role_uma_authorization}	uma_authorization	master	\N	master
569a9736-a11d-45a7-a629-688f48c32c02	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_create-client}	create-client	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
1a8cbb2a-97bc-483e-9395-c72d450b83b4	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-realm}	view-realm	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
67491263-0341-4998-8bce-6681f9779d88	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-users}	view-users	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
cbf4a5e4-acfb-45ca-b05f-8e47913a56ea	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-clients}	view-clients	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
67cbfac4-b232-4c16-9f14-62183b2cd16f	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-events}	view-events	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
31dd0a04-8c5e-44a2-8b61-10ba7a40112c	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-identity-providers}	view-identity-providers	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
8c1bdfe7-06e3-47d3-a7aa-f0fd80d808b2	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_view-authorization}	view-authorization	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
1d9a715e-d2c9-4840-bbd5-1352ba954408	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-realm}	manage-realm	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
399d35a9-6c7c-42ea-a1a2-0d99c1402b5e	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-users}	manage-users	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
e4b9b6ce-f935-4499-8134-1748ca288a74	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-clients}	manage-clients	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
ff928268-d52b-451e-a7f3-827d2b97528f	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-events}	manage-events	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
0bdfecd1-d79b-410a-a43b-c497c01d2633	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-identity-providers}	manage-identity-providers	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
5f14658f-2583-41f6-9520-0bc42404c5d8	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_manage-authorization}	manage-authorization	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
03423dec-7f90-4b7e-ad9a-781ca4cf6623	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_query-users}	query-users	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
f09c49a1-7aa7-42b4-9c09-e394898356a5	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_query-clients}	query-clients	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
aaf6ca62-649f-485c-82f3-4b5f3a27896c	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_query-realms}	query-realms	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
612c2b9f-2564-4182-9f55-02b442f7a237	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_query-groups}	query-groups	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
3ef2be59-d7ba-466c-844d-f82ceb5c4b97	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_realm-admin}	realm-admin	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
87f1454e-e432-44b4-bf4d-382bb21cbadb	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_create-client}	create-client	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
c32ad9a1-5309-4262-bd48-12664830cc12	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-realm}	view-realm	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
d1b59747-3ccc-4a0d-919e-0ef1557115c5	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-users}	view-users	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
53baddab-2f54-432e-93fd-5421c49ed80b	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-clients}	view-clients	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
63fa825c-30fd-472c-9adb-0f534014a8d8	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-events}	view-events	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
b5ad8e57-7b9a-436e-85a8-936f979c3a91	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-identity-providers}	view-identity-providers	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
6ed05576-037c-407f-9417-c7ec27e4ea1c	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_view-authorization}	view-authorization	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
22ea279d-93cc-40ce-8d4f-e5217d240957	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-realm}	manage-realm	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
738a1048-0bac-41de-b61a-729752f27e35	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-users}	manage-users	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
a849a04f-140d-4fc8-9c88-6c7d336eaa33	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-clients}	manage-clients	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
60b3b209-21f4-48d8-b9b1-e7d74beec668	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-events}	manage-events	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
5f328c1f-c3f0-4974-9150-0a5757d8ac5d	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-identity-providers}	manage-identity-providers	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
33f3eea0-dc50-4ab0-b3ea-ce77bac58b67	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_manage-authorization}	manage-authorization	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
70177ccd-0646-4c98-8f41-7c51e7091e73	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_query-users}	query-users	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
cd015335-0897-47d1-9286-14d9075af374	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_query-clients}	query-clients	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
188c5b68-41f9-4a3e-8e09-68d46ef41135	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_query-realms}	query-realms	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
5e05b945-274c-4bf4-a07a-8875303da275	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_query-groups}	query-groups	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
97b672e7-7048-4387-826b-94ae920086c5	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_view-profile}	view-profile	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
657edb13-4e30-46fe-ad93-e8b0663ea6eb	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_manage-account}	manage-account	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
81ba8bdc-69fa-4368-b690-cfed0f3e63ae	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_manage-account-links}	manage-account-links	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
b435a36f-ff7a-4d8f-9a09-faad309d5725	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_view-applications}	view-applications	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
c4610899-b739-4ec3-91ce-67df1ba56f15	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_view-consent}	view-consent	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
930ab436-893c-46e3-9407-81a15834a0f1	3b5949c7-439f-4733-9bc3-16515381a9a1	t	${role_manage-consent}	manage-consent	companyon	3b5949c7-439f-4733-9bc3-16515381a9a1	\N
b9ddbf83-f109-4230-b38e-b4de27c5abca	dfd71606-7771-404f-834d-1dfbb4cfbd05	t	${role_impersonation}	impersonation	master	dfd71606-7771-404f-834d-1dfbb4cfbd05	\N
ee0b2a7d-5b51-4c34-ba87-0efa6f69c514	91591f15-7809-42f6-ba40-659ffc073ec7	t	${role_impersonation}	impersonation	companyon	91591f15-7809-42f6-ba40-659ffc073ec7	\N
5748433b-b9bd-4b9e-af6a-b899adbd59dd	ee58eec6-3349-4569-9dab-ce3563f60b43	t	${role_read-token}	read-token	companyon	ee58eec6-3349-4569-9dab-ce3563f60b43	\N
1499c366-9f74-4260-b11b-3fcbbf1aa75e	companyon	f	${role_offline-access}	offline_access	companyon	\N	companyon
1a4e8fc4-f818-41ed-b4bd-f55b24adcf0f	companyon	f	${role_uma_authorization}	uma_authorization	companyon	\N	companyon
11d4b42d-27ed-4636-bbb7-e1fad780be10	companyon	f	\N	admin	companyon	\N	companyon
2b4d17bb-c8c6-4982-be42-6e7f9b413d68	companyon	f	\N	user	companyon	\N	companyon
\.


--
-- Data for Name: migration_model; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.migration_model (id, version, update_time) FROM stdin;
41ejr	10.0.2	1594454559
\.


--
-- Data for Name: offline_client_session; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.offline_client_session (user_session_id, client_id, offline_flag, "timestamp", data, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: offline_user_session; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.offline_user_session (user_session_id, user_id, realm_id, created_on, offline_flag, data, last_session_refresh) FROM stdin;
\.


--
-- Data for Name: policy_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.policy_config (policy_id, name, value) FROM stdin;
\.


--
-- Data for Name: protocol_mapper; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.protocol_mapper (id, name, protocol, protocol_mapper_name, client_id, client_scope_id) FROM stdin;
1f1e361b-62b7-4488-9712-a27303c9448f	audience resolve	openid-connect	oidc-audience-resolve-mapper	420f22e1-f0e1-407e-af56-07e737bba6bd	\N
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	locale	openid-connect	oidc-usermodel-attribute-mapper	b25108a1-0f49-4b40-a4c3-e0a50ed7e532	\N
3950c8e0-6645-451a-a103-3bbfdd11273f	role list	saml	saml-role-list-mapper	\N	76734ce6-8bfd-40ed-a6d9-e3e509cd30b9
59ab80d3-2d84-41be-abff-dc308c919e23	full name	openid-connect	oidc-full-name-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
d670aa44-d1b8-4c87-95b8-d302fa5eb725	family name	openid-connect	oidc-usermodel-property-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
979d1f46-7ca0-4f41-8f39-6977fac9a10f	given name	openid-connect	oidc-usermodel-property-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
3728b313-cda5-4920-8b7e-1d9c794d183e	middle name	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
dd93c261-8952-4b4f-bd60-d894b7cb3df5	nickname	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	username	openid-connect	oidc-usermodel-property-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
d94d326b-ac9e-478c-b09c-0de8f8c7f372	profile	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
7009ce29-70f7-4bea-aece-214c9514608b	picture	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
28ebf095-d8d6-4678-91a2-88802e59e44b	website	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
823a9cd2-1d76-466f-8451-8a6d692623c6	gender	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
72496a1e-b058-4761-8ccc-045153d22757	birthdate	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
206b572c-1c35-4a3a-9343-c87d4b669667	zoneinfo	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
f428dc7c-f8b9-4f5e-9250-bce86eb46513	locale	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
d9d77ed6-af8c-49a0-94fd-8785e5848458	updated at	openid-connect	oidc-usermodel-attribute-mapper	\N	b3b85baf-867f-4ae1-ae51-b4e1037c49f3
abdea5a7-b850-4c52-9356-0a95fd673b31	email	openid-connect	oidc-usermodel-property-mapper	\N	986138f6-4287-4215-ae66-bebda186a7d5
7a279cc7-0c8b-4a34-9d03-1849431b7a51	email verified	openid-connect	oidc-usermodel-property-mapper	\N	986138f6-4287-4215-ae66-bebda186a7d5
3528a5b7-7259-41e5-8ed4-358ec22a779a	address	openid-connect	oidc-address-mapper	\N	2816a579-86b3-4afb-ba5e-f6bdf0339b9d
7b7d2d27-b17e-44fe-a322-bc1fe4312114	phone number	openid-connect	oidc-usermodel-attribute-mapper	\N	0873f689-ea5f-4b35-990f-9f08976f9fe7
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	phone number verified	openid-connect	oidc-usermodel-attribute-mapper	\N	0873f689-ea5f-4b35-990f-9f08976f9fe7
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	realm roles	openid-connect	oidc-usermodel-realm-role-mapper	\N	5dae4cca-5ac3-4e56-ac42-29244a3e560a
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	client roles	openid-connect	oidc-usermodel-client-role-mapper	\N	5dae4cca-5ac3-4e56-ac42-29244a3e560a
4be30955-be81-4f9b-813c-1a1b0afdf3a4	audience resolve	openid-connect	oidc-audience-resolve-mapper	\N	5dae4cca-5ac3-4e56-ac42-29244a3e560a
18fdb422-6be2-4d39-b02b-4a3c4a1edb77	allowed web origins	openid-connect	oidc-allowed-origins-mapper	\N	f4b7809a-43ce-4884-ab68-fc88aacde2f0
012ed3d9-fbb8-4589-8509-1b5d271d349b	upn	openid-connect	oidc-usermodel-property-mapper	\N	0aae6106-7ce2-49a9-b359-eea980b01322
5139524b-f23b-4614-b8b8-e2e9c995f32b	groups	openid-connect	oidc-usermodel-realm-role-mapper	\N	0aae6106-7ce2-49a9-b359-eea980b01322
d43220f9-65f4-4e25-bbec-d7eb1cdf8cb5	audience resolve	openid-connect	oidc-audience-resolve-mapper	bc4fcf66-a2f7-4363-988e-cef18116ab40	\N
f3a9f9b2-23ec-49a7-9094-4658f57e2f8a	role list	saml	saml-role-list-mapper	\N	7517764d-146d-4f29-87d4-230df456e356
59731d1a-6b8a-427e-bf73-4a25e4deda51	full name	openid-connect	oidc-full-name-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	family name	openid-connect	oidc-usermodel-property-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
9d280fec-78c5-48bc-a649-a6ad668fa40d	given name	openid-connect	oidc-usermodel-property-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
021cdc5f-7567-4374-983b-428fea3a103b	middle name	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
a0e2b6aa-43d8-4480-b439-06846b1aa744	nickname	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
45b4620e-febd-4f79-9718-4af5b66e6ed0	username	openid-connect	oidc-usermodel-property-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	profile	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	picture	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
203d8347-9eb5-4619-9c57-6a6c071f2611	website	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
2c03a0cd-8b36-4d0c-9643-751e8a156206	gender	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
f319ce6b-daac-4c39-a133-0a39af56f88f	birthdate	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	zoneinfo	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	locale	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
cc9e6453-a660-41c1-a17c-9807b799d2ad	updated at	openid-connect	oidc-usermodel-attribute-mapper	\N	3f2baf33-9a02-4c5c-88df-50c2a29b4ca1
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	email	openid-connect	oidc-usermodel-property-mapper	\N	b50a942b-6668-4f80-a7ca-83ae6e3a959a
df15682c-6944-412c-af06-3c894654fc87	email verified	openid-connect	oidc-usermodel-property-mapper	\N	b50a942b-6668-4f80-a7ca-83ae6e3a959a
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	address	openid-connect	oidc-address-mapper	\N	65c63377-ca9b-4724-aea4-e37022dd4fc9
0f06d3fa-363e-49e4-bbc5-753371dcdab4	phone number	openid-connect	oidc-usermodel-attribute-mapper	\N	104ef280-4d18-4d17-87a0-c1c71edea2a3
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	phone number verified	openid-connect	oidc-usermodel-attribute-mapper	\N	104ef280-4d18-4d17-87a0-c1c71edea2a3
ca2310c7-4f85-4520-9da0-3e24848fb7b4	realm roles	openid-connect	oidc-usermodel-realm-role-mapper	\N	28bb5202-fba9-43d9-9145-a21f3be461fb
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	client roles	openid-connect	oidc-usermodel-client-role-mapper	\N	28bb5202-fba9-43d9-9145-a21f3be461fb
a1498676-6200-42e4-a1c5-702857e70419	audience resolve	openid-connect	oidc-audience-resolve-mapper	\N	28bb5202-fba9-43d9-9145-a21f3be461fb
2373672a-add4-41c9-a5fd-f4cde9b9a139	allowed web origins	openid-connect	oidc-allowed-origins-mapper	\N	a8957cd5-e000-4abc-89e8-f67ad7fbf7aa
18b41407-9342-48f6-8e53-df129f3c52f3	upn	openid-connect	oidc-usermodel-property-mapper	\N	1e1e360f-b93d-49e8-b936-17e4da3cc021
cb86fb52-e9b0-4b50-8700-208e55d73932	groups	openid-connect	oidc-usermodel-realm-role-mapper	\N	1e1e360f-b93d-49e8-b936-17e4da3cc021
c46e5280-5498-4955-9254-17864d081111	locale	openid-connect	oidc-usermodel-attribute-mapper	b37d425e-5951-4065-b582-dbefc77d5b61	\N
7c083204-29b8-4b1c-9764-87ecf6b106b2	Username	openid-connect	oidc-usermodel-attribute-mapper	c0bd5757-f83b-46fe-bfb2-38262f50a5bc	\N
e2b98aec-d88b-42e0-8b67-313548a10262	Aud oauth2-resource	openid-connect	oidc-audience-mapper	c0bd5757-f83b-46fe-bfb2-38262f50a5bc	\N
e7d6d963-7758-499e-9ca2-3bc4366a0021	Roles	openid-connect	oidc-usermodel-realm-role-mapper	c0bd5757-f83b-46fe-bfb2-38262f50a5bc	\N
\.


--
-- Data for Name: protocol_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.protocol_mapper_config (protocol_mapper_id, value, name) FROM stdin;
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	true	userinfo.token.claim
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	locale	user.attribute
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	true	id.token.claim
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	true	access.token.claim
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	locale	claim.name
8ed68692-c77f-4ce2-81e7-f71cbbaaa300	String	jsonType.label
3950c8e0-6645-451a-a103-3bbfdd11273f	false	single
3950c8e0-6645-451a-a103-3bbfdd11273f	Basic	attribute.nameformat
3950c8e0-6645-451a-a103-3bbfdd11273f	Role	attribute.name
59ab80d3-2d84-41be-abff-dc308c919e23	true	userinfo.token.claim
59ab80d3-2d84-41be-abff-dc308c919e23	true	id.token.claim
59ab80d3-2d84-41be-abff-dc308c919e23	true	access.token.claim
d670aa44-d1b8-4c87-95b8-d302fa5eb725	true	userinfo.token.claim
d670aa44-d1b8-4c87-95b8-d302fa5eb725	lastName	user.attribute
d670aa44-d1b8-4c87-95b8-d302fa5eb725	true	id.token.claim
d670aa44-d1b8-4c87-95b8-d302fa5eb725	true	access.token.claim
d670aa44-d1b8-4c87-95b8-d302fa5eb725	family_name	claim.name
d670aa44-d1b8-4c87-95b8-d302fa5eb725	String	jsonType.label
979d1f46-7ca0-4f41-8f39-6977fac9a10f	true	userinfo.token.claim
979d1f46-7ca0-4f41-8f39-6977fac9a10f	firstName	user.attribute
979d1f46-7ca0-4f41-8f39-6977fac9a10f	true	id.token.claim
979d1f46-7ca0-4f41-8f39-6977fac9a10f	true	access.token.claim
979d1f46-7ca0-4f41-8f39-6977fac9a10f	given_name	claim.name
979d1f46-7ca0-4f41-8f39-6977fac9a10f	String	jsonType.label
3728b313-cda5-4920-8b7e-1d9c794d183e	true	userinfo.token.claim
3728b313-cda5-4920-8b7e-1d9c794d183e	middleName	user.attribute
3728b313-cda5-4920-8b7e-1d9c794d183e	true	id.token.claim
3728b313-cda5-4920-8b7e-1d9c794d183e	true	access.token.claim
3728b313-cda5-4920-8b7e-1d9c794d183e	middle_name	claim.name
3728b313-cda5-4920-8b7e-1d9c794d183e	String	jsonType.label
dd93c261-8952-4b4f-bd60-d894b7cb3df5	true	userinfo.token.claim
dd93c261-8952-4b4f-bd60-d894b7cb3df5	nickname	user.attribute
dd93c261-8952-4b4f-bd60-d894b7cb3df5	true	id.token.claim
dd93c261-8952-4b4f-bd60-d894b7cb3df5	true	access.token.claim
dd93c261-8952-4b4f-bd60-d894b7cb3df5	nickname	claim.name
dd93c261-8952-4b4f-bd60-d894b7cb3df5	String	jsonType.label
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	true	userinfo.token.claim
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	username	user.attribute
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	true	id.token.claim
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	true	access.token.claim
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	preferred_username	claim.name
4338d107-e1a8-4e42-a6a7-a5de86c2ffb1	String	jsonType.label
d94d326b-ac9e-478c-b09c-0de8f8c7f372	true	userinfo.token.claim
d94d326b-ac9e-478c-b09c-0de8f8c7f372	profile	user.attribute
d94d326b-ac9e-478c-b09c-0de8f8c7f372	true	id.token.claim
d94d326b-ac9e-478c-b09c-0de8f8c7f372	true	access.token.claim
d94d326b-ac9e-478c-b09c-0de8f8c7f372	profile	claim.name
d94d326b-ac9e-478c-b09c-0de8f8c7f372	String	jsonType.label
7009ce29-70f7-4bea-aece-214c9514608b	true	userinfo.token.claim
7009ce29-70f7-4bea-aece-214c9514608b	picture	user.attribute
7009ce29-70f7-4bea-aece-214c9514608b	true	id.token.claim
7009ce29-70f7-4bea-aece-214c9514608b	true	access.token.claim
7009ce29-70f7-4bea-aece-214c9514608b	picture	claim.name
7009ce29-70f7-4bea-aece-214c9514608b	String	jsonType.label
28ebf095-d8d6-4678-91a2-88802e59e44b	true	userinfo.token.claim
28ebf095-d8d6-4678-91a2-88802e59e44b	website	user.attribute
28ebf095-d8d6-4678-91a2-88802e59e44b	true	id.token.claim
28ebf095-d8d6-4678-91a2-88802e59e44b	true	access.token.claim
28ebf095-d8d6-4678-91a2-88802e59e44b	website	claim.name
28ebf095-d8d6-4678-91a2-88802e59e44b	String	jsonType.label
823a9cd2-1d76-466f-8451-8a6d692623c6	true	userinfo.token.claim
823a9cd2-1d76-466f-8451-8a6d692623c6	gender	user.attribute
823a9cd2-1d76-466f-8451-8a6d692623c6	true	id.token.claim
823a9cd2-1d76-466f-8451-8a6d692623c6	true	access.token.claim
823a9cd2-1d76-466f-8451-8a6d692623c6	gender	claim.name
823a9cd2-1d76-466f-8451-8a6d692623c6	String	jsonType.label
72496a1e-b058-4761-8ccc-045153d22757	true	userinfo.token.claim
72496a1e-b058-4761-8ccc-045153d22757	birthdate	user.attribute
72496a1e-b058-4761-8ccc-045153d22757	true	id.token.claim
72496a1e-b058-4761-8ccc-045153d22757	true	access.token.claim
72496a1e-b058-4761-8ccc-045153d22757	birthdate	claim.name
72496a1e-b058-4761-8ccc-045153d22757	String	jsonType.label
206b572c-1c35-4a3a-9343-c87d4b669667	true	userinfo.token.claim
206b572c-1c35-4a3a-9343-c87d4b669667	zoneinfo	user.attribute
206b572c-1c35-4a3a-9343-c87d4b669667	true	id.token.claim
206b572c-1c35-4a3a-9343-c87d4b669667	true	access.token.claim
206b572c-1c35-4a3a-9343-c87d4b669667	zoneinfo	claim.name
206b572c-1c35-4a3a-9343-c87d4b669667	String	jsonType.label
f428dc7c-f8b9-4f5e-9250-bce86eb46513	true	userinfo.token.claim
f428dc7c-f8b9-4f5e-9250-bce86eb46513	locale	user.attribute
f428dc7c-f8b9-4f5e-9250-bce86eb46513	true	id.token.claim
f428dc7c-f8b9-4f5e-9250-bce86eb46513	true	access.token.claim
f428dc7c-f8b9-4f5e-9250-bce86eb46513	locale	claim.name
f428dc7c-f8b9-4f5e-9250-bce86eb46513	String	jsonType.label
d9d77ed6-af8c-49a0-94fd-8785e5848458	true	userinfo.token.claim
d9d77ed6-af8c-49a0-94fd-8785e5848458	updatedAt	user.attribute
d9d77ed6-af8c-49a0-94fd-8785e5848458	true	id.token.claim
d9d77ed6-af8c-49a0-94fd-8785e5848458	true	access.token.claim
d9d77ed6-af8c-49a0-94fd-8785e5848458	updated_at	claim.name
d9d77ed6-af8c-49a0-94fd-8785e5848458	String	jsonType.label
abdea5a7-b850-4c52-9356-0a95fd673b31	true	userinfo.token.claim
abdea5a7-b850-4c52-9356-0a95fd673b31	email	user.attribute
abdea5a7-b850-4c52-9356-0a95fd673b31	true	id.token.claim
abdea5a7-b850-4c52-9356-0a95fd673b31	true	access.token.claim
abdea5a7-b850-4c52-9356-0a95fd673b31	email	claim.name
abdea5a7-b850-4c52-9356-0a95fd673b31	String	jsonType.label
7a279cc7-0c8b-4a34-9d03-1849431b7a51	true	userinfo.token.claim
7a279cc7-0c8b-4a34-9d03-1849431b7a51	emailVerified	user.attribute
7a279cc7-0c8b-4a34-9d03-1849431b7a51	true	id.token.claim
7a279cc7-0c8b-4a34-9d03-1849431b7a51	true	access.token.claim
7a279cc7-0c8b-4a34-9d03-1849431b7a51	email_verified	claim.name
7a279cc7-0c8b-4a34-9d03-1849431b7a51	boolean	jsonType.label
3528a5b7-7259-41e5-8ed4-358ec22a779a	formatted	user.attribute.formatted
3528a5b7-7259-41e5-8ed4-358ec22a779a	country	user.attribute.country
3528a5b7-7259-41e5-8ed4-358ec22a779a	postal_code	user.attribute.postal_code
3528a5b7-7259-41e5-8ed4-358ec22a779a	true	userinfo.token.claim
3528a5b7-7259-41e5-8ed4-358ec22a779a	street	user.attribute.street
3528a5b7-7259-41e5-8ed4-358ec22a779a	true	id.token.claim
3528a5b7-7259-41e5-8ed4-358ec22a779a	region	user.attribute.region
3528a5b7-7259-41e5-8ed4-358ec22a779a	true	access.token.claim
3528a5b7-7259-41e5-8ed4-358ec22a779a	locality	user.attribute.locality
7b7d2d27-b17e-44fe-a322-bc1fe4312114	true	userinfo.token.claim
7b7d2d27-b17e-44fe-a322-bc1fe4312114	phoneNumber	user.attribute
7b7d2d27-b17e-44fe-a322-bc1fe4312114	true	id.token.claim
7b7d2d27-b17e-44fe-a322-bc1fe4312114	true	access.token.claim
7b7d2d27-b17e-44fe-a322-bc1fe4312114	phone_number	claim.name
7b7d2d27-b17e-44fe-a322-bc1fe4312114	String	jsonType.label
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	true	userinfo.token.claim
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	phoneNumberVerified	user.attribute
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	true	id.token.claim
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	true	access.token.claim
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	phone_number_verified	claim.name
9585d8b1-0754-492c-b3c0-cc4ec5b693d1	boolean	jsonType.label
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	true	multivalued
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	foo	user.attribute
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	true	access.token.claim
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	realm_access.roles	claim.name
6e2f7e79-4fbb-4a13-8602-0b406a2f2798	String	jsonType.label
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	true	multivalued
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	foo	user.attribute
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	true	access.token.claim
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	resource_access.${client_id}.roles	claim.name
3e1ae95c-b77d-4374-ba23-1efb2a2e04a8	String	jsonType.label
012ed3d9-fbb8-4589-8509-1b5d271d349b	true	userinfo.token.claim
012ed3d9-fbb8-4589-8509-1b5d271d349b	username	user.attribute
012ed3d9-fbb8-4589-8509-1b5d271d349b	true	id.token.claim
012ed3d9-fbb8-4589-8509-1b5d271d349b	true	access.token.claim
012ed3d9-fbb8-4589-8509-1b5d271d349b	upn	claim.name
012ed3d9-fbb8-4589-8509-1b5d271d349b	String	jsonType.label
5139524b-f23b-4614-b8b8-e2e9c995f32b	true	multivalued
5139524b-f23b-4614-b8b8-e2e9c995f32b	foo	user.attribute
5139524b-f23b-4614-b8b8-e2e9c995f32b	true	id.token.claim
5139524b-f23b-4614-b8b8-e2e9c995f32b	true	access.token.claim
5139524b-f23b-4614-b8b8-e2e9c995f32b	groups	claim.name
5139524b-f23b-4614-b8b8-e2e9c995f32b	String	jsonType.label
f3a9f9b2-23ec-49a7-9094-4658f57e2f8a	false	single
f3a9f9b2-23ec-49a7-9094-4658f57e2f8a	Basic	attribute.nameformat
f3a9f9b2-23ec-49a7-9094-4658f57e2f8a	Role	attribute.name
59731d1a-6b8a-427e-bf73-4a25e4deda51	true	userinfo.token.claim
59731d1a-6b8a-427e-bf73-4a25e4deda51	true	id.token.claim
59731d1a-6b8a-427e-bf73-4a25e4deda51	true	access.token.claim
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	true	userinfo.token.claim
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	lastName	user.attribute
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	true	id.token.claim
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	true	access.token.claim
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	family_name	claim.name
b15f7f83-1ce0-42d9-a508-cb78050b5e0c	String	jsonType.label
9d280fec-78c5-48bc-a649-a6ad668fa40d	true	userinfo.token.claim
9d280fec-78c5-48bc-a649-a6ad668fa40d	firstName	user.attribute
9d280fec-78c5-48bc-a649-a6ad668fa40d	true	id.token.claim
9d280fec-78c5-48bc-a649-a6ad668fa40d	true	access.token.claim
9d280fec-78c5-48bc-a649-a6ad668fa40d	given_name	claim.name
9d280fec-78c5-48bc-a649-a6ad668fa40d	String	jsonType.label
021cdc5f-7567-4374-983b-428fea3a103b	true	userinfo.token.claim
021cdc5f-7567-4374-983b-428fea3a103b	middleName	user.attribute
021cdc5f-7567-4374-983b-428fea3a103b	true	id.token.claim
021cdc5f-7567-4374-983b-428fea3a103b	true	access.token.claim
021cdc5f-7567-4374-983b-428fea3a103b	middle_name	claim.name
021cdc5f-7567-4374-983b-428fea3a103b	String	jsonType.label
a0e2b6aa-43d8-4480-b439-06846b1aa744	true	userinfo.token.claim
a0e2b6aa-43d8-4480-b439-06846b1aa744	nickname	user.attribute
a0e2b6aa-43d8-4480-b439-06846b1aa744	true	id.token.claim
a0e2b6aa-43d8-4480-b439-06846b1aa744	true	access.token.claim
a0e2b6aa-43d8-4480-b439-06846b1aa744	nickname	claim.name
a0e2b6aa-43d8-4480-b439-06846b1aa744	String	jsonType.label
45b4620e-febd-4f79-9718-4af5b66e6ed0	true	userinfo.token.claim
45b4620e-febd-4f79-9718-4af5b66e6ed0	username	user.attribute
45b4620e-febd-4f79-9718-4af5b66e6ed0	true	id.token.claim
45b4620e-febd-4f79-9718-4af5b66e6ed0	true	access.token.claim
45b4620e-febd-4f79-9718-4af5b66e6ed0	preferred_username	claim.name
45b4620e-febd-4f79-9718-4af5b66e6ed0	String	jsonType.label
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	true	userinfo.token.claim
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	profile	user.attribute
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	true	id.token.claim
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	true	access.token.claim
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	profile	claim.name
a1e13d94-9f51-48f4-95ea-dfed47c9ad21	String	jsonType.label
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	true	userinfo.token.claim
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	picture	user.attribute
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	true	id.token.claim
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	true	access.token.claim
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	picture	claim.name
c7ec0bea-9a3c-4037-a384-0f972e2b26fd	String	jsonType.label
203d8347-9eb5-4619-9c57-6a6c071f2611	true	userinfo.token.claim
203d8347-9eb5-4619-9c57-6a6c071f2611	website	user.attribute
203d8347-9eb5-4619-9c57-6a6c071f2611	true	id.token.claim
203d8347-9eb5-4619-9c57-6a6c071f2611	true	access.token.claim
203d8347-9eb5-4619-9c57-6a6c071f2611	website	claim.name
203d8347-9eb5-4619-9c57-6a6c071f2611	String	jsonType.label
2c03a0cd-8b36-4d0c-9643-751e8a156206	true	userinfo.token.claim
2c03a0cd-8b36-4d0c-9643-751e8a156206	gender	user.attribute
2c03a0cd-8b36-4d0c-9643-751e8a156206	true	id.token.claim
2c03a0cd-8b36-4d0c-9643-751e8a156206	true	access.token.claim
2c03a0cd-8b36-4d0c-9643-751e8a156206	gender	claim.name
2c03a0cd-8b36-4d0c-9643-751e8a156206	String	jsonType.label
f319ce6b-daac-4c39-a133-0a39af56f88f	true	userinfo.token.claim
f319ce6b-daac-4c39-a133-0a39af56f88f	birthdate	user.attribute
f319ce6b-daac-4c39-a133-0a39af56f88f	true	id.token.claim
f319ce6b-daac-4c39-a133-0a39af56f88f	true	access.token.claim
f319ce6b-daac-4c39-a133-0a39af56f88f	birthdate	claim.name
f319ce6b-daac-4c39-a133-0a39af56f88f	String	jsonType.label
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	true	userinfo.token.claim
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	zoneinfo	user.attribute
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	true	id.token.claim
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	true	access.token.claim
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	zoneinfo	claim.name
d4b758ec-84b0-4c38-bb9b-5e56ca2b0c0c	String	jsonType.label
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	true	userinfo.token.claim
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	locale	user.attribute
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	true	id.token.claim
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	true	access.token.claim
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	locale	claim.name
097d2078-ebb7-47f3-a32e-9e8c3fe6b100	String	jsonType.label
cc9e6453-a660-41c1-a17c-9807b799d2ad	true	userinfo.token.claim
cc9e6453-a660-41c1-a17c-9807b799d2ad	updatedAt	user.attribute
cc9e6453-a660-41c1-a17c-9807b799d2ad	true	id.token.claim
cc9e6453-a660-41c1-a17c-9807b799d2ad	true	access.token.claim
cc9e6453-a660-41c1-a17c-9807b799d2ad	updated_at	claim.name
cc9e6453-a660-41c1-a17c-9807b799d2ad	String	jsonType.label
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	true	userinfo.token.claim
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	email	user.attribute
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	true	id.token.claim
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	true	access.token.claim
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	email	claim.name
0ffbd543-f1d0-4779-90c7-cdaa4d67741e	String	jsonType.label
df15682c-6944-412c-af06-3c894654fc87	true	userinfo.token.claim
df15682c-6944-412c-af06-3c894654fc87	emailVerified	user.attribute
df15682c-6944-412c-af06-3c894654fc87	true	id.token.claim
df15682c-6944-412c-af06-3c894654fc87	true	access.token.claim
df15682c-6944-412c-af06-3c894654fc87	email_verified	claim.name
df15682c-6944-412c-af06-3c894654fc87	boolean	jsonType.label
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	formatted	user.attribute.formatted
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	country	user.attribute.country
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	postal_code	user.attribute.postal_code
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	true	userinfo.token.claim
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	street	user.attribute.street
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	true	id.token.claim
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	region	user.attribute.region
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	true	access.token.claim
87e4ab59-45b9-41a0-a19a-9c9dd8d9bb97	locality	user.attribute.locality
0f06d3fa-363e-49e4-bbc5-753371dcdab4	true	userinfo.token.claim
0f06d3fa-363e-49e4-bbc5-753371dcdab4	phoneNumber	user.attribute
0f06d3fa-363e-49e4-bbc5-753371dcdab4	true	id.token.claim
0f06d3fa-363e-49e4-bbc5-753371dcdab4	true	access.token.claim
0f06d3fa-363e-49e4-bbc5-753371dcdab4	phone_number	claim.name
0f06d3fa-363e-49e4-bbc5-753371dcdab4	String	jsonType.label
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	true	userinfo.token.claim
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	phoneNumberVerified	user.attribute
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	true	id.token.claim
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	true	access.token.claim
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	phone_number_verified	claim.name
e9cb61b4-cc31-4c7d-882d-8e09da5313a1	boolean	jsonType.label
ca2310c7-4f85-4520-9da0-3e24848fb7b4	true	multivalued
ca2310c7-4f85-4520-9da0-3e24848fb7b4	foo	user.attribute
ca2310c7-4f85-4520-9da0-3e24848fb7b4	true	access.token.claim
ca2310c7-4f85-4520-9da0-3e24848fb7b4	realm_access.roles	claim.name
ca2310c7-4f85-4520-9da0-3e24848fb7b4	String	jsonType.label
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	true	multivalued
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	foo	user.attribute
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	true	access.token.claim
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	resource_access.${client_id}.roles	claim.name
6e6dfcb4-c4ae-40c7-b381-e8efd27e662d	String	jsonType.label
18b41407-9342-48f6-8e53-df129f3c52f3	true	userinfo.token.claim
18b41407-9342-48f6-8e53-df129f3c52f3	username	user.attribute
18b41407-9342-48f6-8e53-df129f3c52f3	true	id.token.claim
18b41407-9342-48f6-8e53-df129f3c52f3	true	access.token.claim
18b41407-9342-48f6-8e53-df129f3c52f3	upn	claim.name
18b41407-9342-48f6-8e53-df129f3c52f3	String	jsonType.label
cb86fb52-e9b0-4b50-8700-208e55d73932	true	multivalued
cb86fb52-e9b0-4b50-8700-208e55d73932	foo	user.attribute
cb86fb52-e9b0-4b50-8700-208e55d73932	true	id.token.claim
cb86fb52-e9b0-4b50-8700-208e55d73932	true	access.token.claim
cb86fb52-e9b0-4b50-8700-208e55d73932	groups	claim.name
cb86fb52-e9b0-4b50-8700-208e55d73932	String	jsonType.label
c46e5280-5498-4955-9254-17864d081111	true	userinfo.token.claim
c46e5280-5498-4955-9254-17864d081111	locale	user.attribute
c46e5280-5498-4955-9254-17864d081111	true	id.token.claim
c46e5280-5498-4955-9254-17864d081111	true	access.token.claim
c46e5280-5498-4955-9254-17864d081111	locale	claim.name
c46e5280-5498-4955-9254-17864d081111	String	jsonType.label
7c083204-29b8-4b1c-9764-87ecf6b106b2	true	userinfo.token.claim
7c083204-29b8-4b1c-9764-87ecf6b106b2	username	user.attribute
7c083204-29b8-4b1c-9764-87ecf6b106b2	true	id.token.claim
7c083204-29b8-4b1c-9764-87ecf6b106b2	true	access.token.claim
7c083204-29b8-4b1c-9764-87ecf6b106b2	user_name	claim.name
7c083204-29b8-4b1c-9764-87ecf6b106b2	String	jsonType.label
e2b98aec-d88b-42e0-8b67-313548a10262	false	id.token.claim
e2b98aec-d88b-42e0-8b67-313548a10262	true	access.token.claim
e2b98aec-d88b-42e0-8b67-313548a10262	oauth2-resource	included.custom.audience
e7d6d963-7758-499e-9ca2-3bc4366a0021	true	id.token.claim
e7d6d963-7758-499e-9ca2-3bc4366a0021	true	access.token.claim
e7d6d963-7758-499e-9ca2-3bc4366a0021	authorities	claim.name
e7d6d963-7758-499e-9ca2-3bc4366a0021	true	multivalued
e7d6d963-7758-499e-9ca2-3bc4366a0021	true	userinfo.token.claim
\.


--
-- Data for Name: realm; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm (id, access_code_lifespan, user_action_lifespan, access_token_lifespan, account_theme, admin_theme, email_theme, enabled, events_enabled, events_expiration, login_theme, name, not_before, password_policy, registration_allowed, remember_me, reset_password_allowed, social, ssl_required, sso_idle_timeout, sso_max_lifespan, update_profile_on_soc_login, verify_email, master_admin_client, login_lifespan, internationalization_enabled, default_locale, reg_email_as_username, admin_events_enabled, admin_events_details_enabled, edit_username_allowed, otp_policy_counter, otp_policy_window, otp_policy_period, otp_policy_digits, otp_policy_alg, otp_policy_type, browser_flow, registration_flow, direct_grant_flow, reset_credentials_flow, client_auth_flow, offline_session_idle_timeout, revoke_refresh_token, access_token_life_implicit, login_with_email_allowed, duplicate_emails_allowed, docker_auth_flow, refresh_token_max_reuse, allow_user_managed_access, sso_max_lifespan_remember_me, sso_idle_timeout_remember_me) FROM stdin;
master	60	300	60	\N	\N	\N	t	f	0	\N	master	0	\N	f	f	f	f	EXTERNAL	1800	36000	f	f	4acfb22d-b9a5-4cf7-b885-c2570bae9106	1800	f	\N	f	f	f	f	0	1	30	6	HmacSHA1	totp	d48c5340-1fc3-49ae-8d3e-f663d05bc01b	f9fbd9d2-e06e-4712-aa72-60fbe9de6ff2	3f90150b-ec74-4785-a812-fa9cb9182a8d	59dc226c-f46b-48ab-9b08-7dd56c23bb6b	c9252a9a-85a1-4fa4-8b8e-97334162f9be	2592000	f	900	t	f	ad877157-64ea-4b8a-87f2-2f397cb07148	0	f	0	0
companyon	60	300	300	\N	\N	\N	t	f	0	\N	companyon	0	\N	f	f	f	f	EXTERNAL	1800	36000	f	f	dfd71606-7771-404f-834d-1dfbb4cfbd05	1800	f	\N	f	f	f	f	0	1	30	6	HmacSHA1	totp	2649ce2b-8114-4222-b5c8-8e114a558e89	bb64b3cf-27be-4f9e-b2f9-faf8741d012d	b982bc77-2af2-4691-bda9-e0e42840ba20	7e59a0a5-66de-4a37-9ba3-f5107797782a	b1979cd8-80cb-4199-96e1-d8fdad08b375	2592000	f	900	t	f	261090cf-a13d-406e-b99e-91aace2ae74a	0	f	0	0
\.


--
-- Data for Name: realm_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_attribute (name, value, realm_id) FROM stdin;
_browser_header.contentSecurityPolicyReportOnly		master
_browser_header.xContentTypeOptions	nosniff	master
_browser_header.xRobotsTag	none	master
_browser_header.xFrameOptions	SAMEORIGIN	master
_browser_header.contentSecurityPolicy	frame-src 'self'; frame-ancestors 'self'; object-src 'none';	master
_browser_header.xXSSProtection	1; mode=block	master
_browser_header.strictTransportSecurity	max-age=31536000; includeSubDomains	master
bruteForceProtected	false	master
permanentLockout	false	master
maxFailureWaitSeconds	900	master
minimumQuickLoginWaitSeconds	60	master
waitIncrementSeconds	60	master
quickLoginCheckMilliSeconds	1000	master
maxDeltaTimeSeconds	43200	master
failureFactor	30	master
displayName	Keycloak	master
displayNameHtml	<div class="kc-logo-text"><span>Keycloak</span></div>	master
offlineSessionMaxLifespanEnabled	false	master
offlineSessionMaxLifespan	5184000	master
_browser_header.contentSecurityPolicyReportOnly		companyon
_browser_header.xContentTypeOptions	nosniff	companyon
_browser_header.xRobotsTag	none	companyon
_browser_header.xFrameOptions	SAMEORIGIN	companyon
_browser_header.contentSecurityPolicy	frame-src 'self'; frame-ancestors 'self'; object-src 'none';	companyon
_browser_header.xXSSProtection	1; mode=block	companyon
_browser_header.strictTransportSecurity	max-age=31536000; includeSubDomains	companyon
bruteForceProtected	false	companyon
permanentLockout	false	companyon
maxFailureWaitSeconds	900	companyon
minimumQuickLoginWaitSeconds	60	companyon
waitIncrementSeconds	60	companyon
quickLoginCheckMilliSeconds	1000	companyon
maxDeltaTimeSeconds	43200	companyon
failureFactor	30	companyon
offlineSessionMaxLifespanEnabled	false	companyon
offlineSessionMaxLifespan	5184000	companyon
actionTokenGeneratedByAdminLifespan	43200	companyon
actionTokenGeneratedByUserLifespan	300	companyon
webAuthnPolicyRpEntityName	keycloak	companyon
webAuthnPolicySignatureAlgorithms	ES256	companyon
webAuthnPolicyRpId		companyon
webAuthnPolicyAttestationConveyancePreference	not specified	companyon
webAuthnPolicyAuthenticatorAttachment	not specified	companyon
webAuthnPolicyRequireResidentKey	not specified	companyon
webAuthnPolicyUserVerificationRequirement	not specified	companyon
webAuthnPolicyCreateTimeout	0	companyon
webAuthnPolicyAvoidSameAuthenticatorRegister	false	companyon
webAuthnPolicyRpEntityNamePasswordless	keycloak	companyon
webAuthnPolicySignatureAlgorithmsPasswordless	ES256	companyon
webAuthnPolicyRpIdPasswordless		companyon
webAuthnPolicyAttestationConveyancePreferencePasswordless	not specified	companyon
webAuthnPolicyAuthenticatorAttachmentPasswordless	not specified	companyon
webAuthnPolicyRequireResidentKeyPasswordless	not specified	companyon
webAuthnPolicyUserVerificationRequirementPasswordless	not specified	companyon
webAuthnPolicyCreateTimeoutPasswordless	0	companyon
webAuthnPolicyAvoidSameAuthenticatorRegisterPasswordless	false	companyon
\.


--
-- Data for Name: realm_default_groups; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_default_groups (realm_id, group_id) FROM stdin;
\.


--
-- Data for Name: realm_default_roles; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_default_roles (realm_id, role_id) FROM stdin;
master	c69b1cac-655d-4ae8-9f11-2cc03e00bf8e
master	a5eaee59-8ec0-4c56-a10d-02d4222d840e
companyon	1499c366-9f74-4260-b11b-3fcbbf1aa75e
companyon	1a4e8fc4-f818-41ed-b4bd-f55b24adcf0f
\.


--
-- Data for Name: realm_enabled_event_types; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_enabled_event_types (realm_id, value) FROM stdin;
\.


--
-- Data for Name: realm_events_listeners; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_events_listeners (realm_id, value) FROM stdin;
master	jboss-logging
companyon	jboss-logging
\.


--
-- Data for Name: realm_required_credential; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_required_credential (type, form_label, input, secret, realm_id) FROM stdin;
password	password	t	t	master
password	password	t	t	companyon
\.


--
-- Data for Name: realm_smtp_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_smtp_config (realm_id, value, name) FROM stdin;
\.


--
-- Data for Name: realm_supported_locales; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.realm_supported_locales (realm_id, value) FROM stdin;
\.


--
-- Data for Name: redirect_uris; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.redirect_uris (client_id, value) FROM stdin;
25761e29-96d5-4661-a01e-744abbe404b2	/realms/master/account/*
420f22e1-f0e1-407e-af56-07e737bba6bd	/realms/master/account/*
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	/admin/master/console/*
3b5949c7-439f-4733-9bc3-16515381a9a1	/realms/companyon/account/*
bc4fcf66-a2f7-4363-988e-cef18116ab40	/realms/companyon/account/*
b37d425e-5951-4065-b582-dbefc77d5b61	/admin/companyon/console/*
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	http://localhost:3000/*
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	http://localhost/*
\.


--
-- Data for Name: required_action_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.required_action_config (required_action_id, value, name) FROM stdin;
\.


--
-- Data for Name: required_action_provider; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.required_action_provider (id, alias, name, realm_id, enabled, default_action, provider_id, priority) FROM stdin;
9c766989-0fd1-43cc-b95a-80a60f77bb75	VERIFY_EMAIL	Verify Email	master	t	f	VERIFY_EMAIL	50
63ae4613-b897-42dd-b721-9d54f24f5236	UPDATE_PROFILE	Update Profile	master	t	f	UPDATE_PROFILE	40
4822f200-3a87-4b7e-97b7-4050c3ea040e	CONFIGURE_TOTP	Configure OTP	master	t	f	CONFIGURE_TOTP	10
f3fc9d79-ef17-4062-9ba1-672b779f3915	UPDATE_PASSWORD	Update Password	master	t	f	UPDATE_PASSWORD	30
afba671b-9f53-4784-a2d2-f1c22e5b023e	terms_and_conditions	Terms and Conditions	master	f	f	terms_and_conditions	20
6723e2aa-0405-4961-8d1a-174dd786885c	update_user_locale	Update User Locale	master	t	f	update_user_locale	1000
3d4d0ab6-e500-483d-8e7e-6e9398e8d096	VERIFY_EMAIL	Verify Email	companyon	t	f	VERIFY_EMAIL	50
d9d856c9-29bd-49f7-a90e-d0c96c68f20d	UPDATE_PROFILE	Update Profile	companyon	t	f	UPDATE_PROFILE	40
293d342a-7a9b-44e4-ad7b-a0fe7ac13999	CONFIGURE_TOTP	Configure OTP	companyon	t	f	CONFIGURE_TOTP	10
71ee08e8-d1a4-46e5-8886-5aac7bb8cb50	UPDATE_PASSWORD	Update Password	companyon	t	f	UPDATE_PASSWORD	30
b14b296e-2562-4144-974f-2a7148fa2c71	terms_and_conditions	Terms and Conditions	companyon	f	f	terms_and_conditions	20
fd8b768a-92a0-49ad-92bc-f379238c9316	update_user_locale	Update User Locale	companyon	t	f	update_user_locale	1000
\.


--
-- Data for Name: resource_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_attribute (id, name, value, resource_id) FROM stdin;
\.


--
-- Data for Name: resource_policy; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_policy (resource_id, policy_id) FROM stdin;
\.


--
-- Data for Name: resource_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_scope (resource_id, scope_id) FROM stdin;
\.


--
-- Data for Name: resource_server; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_server (id, allow_rs_remote_mgmt, policy_enforce_mode, decision_strategy) FROM stdin;
\.


--
-- Data for Name: resource_server_perm_ticket; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_server_perm_ticket (id, owner, requester, created_timestamp, granted_timestamp, resource_id, scope_id, resource_server_id, policy_id) FROM stdin;
\.


--
-- Data for Name: resource_server_policy; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_server_policy (id, name, description, type, decision_strategy, logic, resource_server_id, owner) FROM stdin;
\.


--
-- Data for Name: resource_server_resource; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_server_resource (id, name, type, icon_uri, owner, resource_server_id, owner_managed_access, display_name) FROM stdin;
\.


--
-- Data for Name: resource_server_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_server_scope (id, name, icon_uri, resource_server_id, display_name) FROM stdin;
\.


--
-- Data for Name: resource_uris; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.resource_uris (resource_id, value) FROM stdin;
\.


--
-- Data for Name: role_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.role_attribute (id, role_id, name, value) FROM stdin;
\.


--
-- Data for Name: scope_mapping; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.scope_mapping (client_id, role_id) FROM stdin;
420f22e1-f0e1-407e-af56-07e737bba6bd	027dd772-2415-4346-a5f2-3f2ed5d2424a
bc4fcf66-a2f7-4363-988e-cef18116ab40	657edb13-4e30-46fe-ad93-e8b0663ea6eb
\.


--
-- Data for Name: scope_policy; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.scope_policy (scope_id, policy_id) FROM stdin;
\.


--
-- Data for Name: user_attribute; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_attribute (name, value, user_id, id) FROM stdin;
\.


--
-- Data for Name: user_consent; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_consent (id, client_id, user_id, created_date, last_updated_date, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: user_consent_client_scope; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_consent_client_scope (user_consent_id, scope_id) FROM stdin;
\.


--
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_entity (id, email, email_constraint, email_verified, enabled, federation_link, first_name, last_name, realm_id, username, created_timestamp, service_account_client_link, not_before) FROM stdin;
69ddc4a0-0d05-4d13-a54f-b47e23986d5f	\N	b5d559bb-4aad-4133-87fc-6b56024e3543	f	t	\N	\N	\N	master	admin	1594454560990	\N	0
cee9cb3c-f6da-4ba5-a510-2b811e3900fd	testuser@companyon.de	testuser@companyon.de	f	t	\N	Test	User	companyon	testuser	1594457218369	\N	0
1891c4b4-3f8a-4744-8688-f84c4e133bde	admin@companyon.de	admin@companyon.de	f	t	\N	Admin	Root	companyon	admin	1594457176705	\N	0
\.


--
-- Data for Name: user_federation_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_federation_config (user_federation_provider_id, value, name) FROM stdin;
\.


--
-- Data for Name: user_federation_mapper; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_federation_mapper (id, name, federation_provider_id, federation_mapper_type, realm_id) FROM stdin;
\.


--
-- Data for Name: user_federation_mapper_config; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_federation_mapper_config (user_federation_mapper_id, value, name) FROM stdin;
\.


--
-- Data for Name: user_federation_provider; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_federation_provider (id, changed_sync_period, display_name, full_sync_period, last_sync, priority, provider_name, realm_id) FROM stdin;
\.


--
-- Data for Name: user_group_membership; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_group_membership (group_id, user_id) FROM stdin;
b640082c-f2d1-4d00-8746-d30a27a44013	1891c4b4-3f8a-4744-8688-f84c4e133bde
92faae8d-5622-4929-b504-968fc7a7ec65	cee9cb3c-f6da-4ba5-a510-2b811e3900fd
\.


--
-- Data for Name: user_required_action; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_required_action (user_id, required_action) FROM stdin;
\.


--
-- Data for Name: user_role_mapping; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_role_mapping (role_id, user_id) FROM stdin;
524c815d-7089-4723-be67-fad4e743e865	69ddc4a0-0d05-4d13-a54f-b47e23986d5f
a5eaee59-8ec0-4c56-a10d-02d4222d840e	69ddc4a0-0d05-4d13-a54f-b47e23986d5f
027dd772-2415-4346-a5f2-3f2ed5d2424a	69ddc4a0-0d05-4d13-a54f-b47e23986d5f
c69b1cac-655d-4ae8-9f11-2cc03e00bf8e	69ddc4a0-0d05-4d13-a54f-b47e23986d5f
3e3ed1d1-afb6-477e-adc4-799b2879585b	69ddc4a0-0d05-4d13-a54f-b47e23986d5f
1a4e8fc4-f818-41ed-b4bd-f55b24adcf0f	1891c4b4-3f8a-4744-8688-f84c4e133bde
657edb13-4e30-46fe-ad93-e8b0663ea6eb	1891c4b4-3f8a-4744-8688-f84c4e133bde
1499c366-9f74-4260-b11b-3fcbbf1aa75e	1891c4b4-3f8a-4744-8688-f84c4e133bde
97b672e7-7048-4387-826b-94ae920086c5	1891c4b4-3f8a-4744-8688-f84c4e133bde
1a4e8fc4-f818-41ed-b4bd-f55b24adcf0f	cee9cb3c-f6da-4ba5-a510-2b811e3900fd
657edb13-4e30-46fe-ad93-e8b0663ea6eb	cee9cb3c-f6da-4ba5-a510-2b811e3900fd
1499c366-9f74-4260-b11b-3fcbbf1aa75e	cee9cb3c-f6da-4ba5-a510-2b811e3900fd
97b672e7-7048-4387-826b-94ae920086c5	cee9cb3c-f6da-4ba5-a510-2b811e3900fd
\.


--
-- Data for Name: user_session; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_session (id, auth_method, ip_address, last_session_refresh, login_username, realm_id, remember_me, started, user_id, user_session_state, broker_session_id, broker_user_id) FROM stdin;
\.


--
-- Data for Name: user_session_note; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.user_session_note (user_session, name, value) FROM stdin;
\.


--
-- Data for Name: username_login_failure; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.username_login_failure (realm_id, username, failed_login_not_before, last_failure, last_ip_failure, num_failures) FROM stdin;
\.


--
-- Data for Name: web_origins; Type: TABLE DATA; Schema: public; Owner: keycloak
--

COPY public.web_origins (client_id, value) FROM stdin;
b25108a1-0f49-4b40-a4c3-e0a50ed7e532	+
b37d425e-5951-4065-b582-dbefc77d5b61	+
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	http://localhost
c0bd5757-f83b-46fe-bfb2-38262f50a5bc	http://localhost:3000
\.


--
-- Name: username_login_failure CONSTRAINT_17-2; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.username_login_failure
    ADD CONSTRAINT "CONSTRAINT_17-2" PRIMARY KEY (realm_id, username);


--
-- Name: keycloak_role UK_J3RWUVD56ONTGSUHOGM184WW2-2; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT "UK_J3RWUVD56ONTGSUHOGM184WW2-2" UNIQUE (name, client_realm_constraint);


--
-- Name: client_auth_flow_bindings c_cli_flow_bind; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_auth_flow_bindings
    ADD CONSTRAINT c_cli_flow_bind PRIMARY KEY (client_id, binding_name);


--
-- Name: client_scope_client c_cli_scope_bind; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_client
    ADD CONSTRAINT c_cli_scope_bind PRIMARY KEY (client_id, scope_id);


--
-- Name: client_initial_access cnstr_client_init_acc_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_initial_access
    ADD CONSTRAINT cnstr_client_init_acc_pk PRIMARY KEY (id);


--
-- Name: realm_default_groups con_group_id_def_groups; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT con_group_id_def_groups UNIQUE (group_id);


--
-- Name: broker_link constr_broker_link_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.broker_link
    ADD CONSTRAINT constr_broker_link_pk PRIMARY KEY (identity_provider, user_id);


--
-- Name: client_user_session_note constr_cl_usr_ses_note; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_user_session_note
    ADD CONSTRAINT constr_cl_usr_ses_note PRIMARY KEY (client_session, name);


--
-- Name: client_default_roles constr_client_default_roles; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_default_roles
    ADD CONSTRAINT constr_client_default_roles PRIMARY KEY (client_id, role_id);


--
-- Name: component_config constr_component_config_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.component_config
    ADD CONSTRAINT constr_component_config_pk PRIMARY KEY (id);


--
-- Name: component constr_component_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.component
    ADD CONSTRAINT constr_component_pk PRIMARY KEY (id);


--
-- Name: fed_user_required_action constr_fed_required_action; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_required_action
    ADD CONSTRAINT constr_fed_required_action PRIMARY KEY (required_action, user_id);


--
-- Name: fed_user_attribute constr_fed_user_attr_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_attribute
    ADD CONSTRAINT constr_fed_user_attr_pk PRIMARY KEY (id);


--
-- Name: fed_user_consent constr_fed_user_consent_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_consent
    ADD CONSTRAINT constr_fed_user_consent_pk PRIMARY KEY (id);


--
-- Name: fed_user_credential constr_fed_user_cred_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_credential
    ADD CONSTRAINT constr_fed_user_cred_pk PRIMARY KEY (id);


--
-- Name: fed_user_group_membership constr_fed_user_group; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_group_membership
    ADD CONSTRAINT constr_fed_user_group PRIMARY KEY (group_id, user_id);


--
-- Name: fed_user_role_mapping constr_fed_user_role; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_role_mapping
    ADD CONSTRAINT constr_fed_user_role PRIMARY KEY (role_id, user_id);


--
-- Name: federated_user constr_federated_user; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.federated_user
    ADD CONSTRAINT constr_federated_user PRIMARY KEY (id);


--
-- Name: realm_default_groups constr_realm_default_groups; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT constr_realm_default_groups PRIMARY KEY (realm_id, group_id);


--
-- Name: realm_enabled_event_types constr_realm_enabl_event_types; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_enabled_event_types
    ADD CONSTRAINT constr_realm_enabl_event_types PRIMARY KEY (realm_id, value);


--
-- Name: realm_events_listeners constr_realm_events_listeners; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_events_listeners
    ADD CONSTRAINT constr_realm_events_listeners PRIMARY KEY (realm_id, value);


--
-- Name: realm_supported_locales constr_realm_supported_locales; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_supported_locales
    ADD CONSTRAINT constr_realm_supported_locales PRIMARY KEY (realm_id, value);


--
-- Name: identity_provider constraint_2b; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT constraint_2b PRIMARY KEY (internal_id);


--
-- Name: client_attributes constraint_3c; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_attributes
    ADD CONSTRAINT constraint_3c PRIMARY KEY (client_id, name);


--
-- Name: event_entity constraint_4; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.event_entity
    ADD CONSTRAINT constraint_4 PRIMARY KEY (id);


--
-- Name: federated_identity constraint_40; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.federated_identity
    ADD CONSTRAINT constraint_40 PRIMARY KEY (identity_provider, user_id);


--
-- Name: realm constraint_4a; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm
    ADD CONSTRAINT constraint_4a PRIMARY KEY (id);


--
-- Name: client_session_role constraint_5; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_role
    ADD CONSTRAINT constraint_5 PRIMARY KEY (client_session, role_id);


--
-- Name: user_session constraint_57; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT constraint_57 PRIMARY KEY (id);


--
-- Name: user_federation_provider constraint_5c; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_provider
    ADD CONSTRAINT constraint_5c PRIMARY KEY (id);


--
-- Name: client_session_note constraint_5e; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_note
    ADD CONSTRAINT constraint_5e PRIMARY KEY (client_session, name);


--
-- Name: client constraint_7; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT constraint_7 PRIMARY KEY (id);


--
-- Name: client_session constraint_8; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session
    ADD CONSTRAINT constraint_8 PRIMARY KEY (id);


--
-- Name: scope_mapping constraint_81; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_mapping
    ADD CONSTRAINT constraint_81 PRIMARY KEY (client_id, role_id);


--
-- Name: client_node_registrations constraint_84; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_node_registrations
    ADD CONSTRAINT constraint_84 PRIMARY KEY (client_id, name);


--
-- Name: realm_attribute constraint_9; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_attribute
    ADD CONSTRAINT constraint_9 PRIMARY KEY (name, realm_id);


--
-- Name: realm_required_credential constraint_92; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_required_credential
    ADD CONSTRAINT constraint_92 PRIMARY KEY (realm_id, type);


--
-- Name: keycloak_role constraint_a; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT constraint_a PRIMARY KEY (id);


--
-- Name: admin_event_entity constraint_admin_event_entity; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.admin_event_entity
    ADD CONSTRAINT constraint_admin_event_entity PRIMARY KEY (id);


--
-- Name: authenticator_config_entry constraint_auth_cfg_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authenticator_config_entry
    ADD CONSTRAINT constraint_auth_cfg_pk PRIMARY KEY (authenticator_id, name);


--
-- Name: authentication_execution constraint_auth_exec_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT constraint_auth_exec_pk PRIMARY KEY (id);


--
-- Name: authentication_flow constraint_auth_flow_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authentication_flow
    ADD CONSTRAINT constraint_auth_flow_pk PRIMARY KEY (id);


--
-- Name: authenticator_config constraint_auth_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authenticator_config
    ADD CONSTRAINT constraint_auth_pk PRIMARY KEY (id);


--
-- Name: client_session_auth_status constraint_auth_status_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_auth_status
    ADD CONSTRAINT constraint_auth_status_pk PRIMARY KEY (client_session, authenticator);


--
-- Name: user_role_mapping constraint_c; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_role_mapping
    ADD CONSTRAINT constraint_c PRIMARY KEY (role_id, user_id);


--
-- Name: composite_role constraint_composite_role; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT constraint_composite_role PRIMARY KEY (composite, child_role);


--
-- Name: client_session_prot_mapper constraint_cs_pmp_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_prot_mapper
    ADD CONSTRAINT constraint_cs_pmp_pk PRIMARY KEY (client_session, protocol_mapper_id);


--
-- Name: identity_provider_config constraint_d; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider_config
    ADD CONSTRAINT constraint_d PRIMARY KEY (identity_provider_id, name);


--
-- Name: policy_config constraint_dpc; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.policy_config
    ADD CONSTRAINT constraint_dpc PRIMARY KEY (policy_id, name);


--
-- Name: realm_smtp_config constraint_e; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_smtp_config
    ADD CONSTRAINT constraint_e PRIMARY KEY (realm_id, name);


--
-- Name: credential constraint_f; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.credential
    ADD CONSTRAINT constraint_f PRIMARY KEY (id);


--
-- Name: user_federation_config constraint_f9; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_config
    ADD CONSTRAINT constraint_f9 PRIMARY KEY (user_federation_provider_id, name);


--
-- Name: resource_server_perm_ticket constraint_fapmt; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT constraint_fapmt PRIMARY KEY (id);


--
-- Name: resource_server_resource constraint_farsr; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT constraint_farsr PRIMARY KEY (id);


--
-- Name: resource_server_policy constraint_farsrp; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT constraint_farsrp PRIMARY KEY (id);


--
-- Name: associated_policy constraint_farsrpap; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT constraint_farsrpap PRIMARY KEY (policy_id, associated_policy_id);


--
-- Name: resource_policy constraint_farsrpp; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT constraint_farsrpp PRIMARY KEY (resource_id, policy_id);


--
-- Name: resource_server_scope constraint_farsrs; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT constraint_farsrs PRIMARY KEY (id);


--
-- Name: resource_scope constraint_farsrsp; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT constraint_farsrsp PRIMARY KEY (resource_id, scope_id);


--
-- Name: scope_policy constraint_farsrsps; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT constraint_farsrsps PRIMARY KEY (scope_id, policy_id);


--
-- Name: user_entity constraint_fb; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT constraint_fb PRIMARY KEY (id);


--
-- Name: user_federation_mapper_config constraint_fedmapper_cfg_pm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_mapper_config
    ADD CONSTRAINT constraint_fedmapper_cfg_pm PRIMARY KEY (user_federation_mapper_id, name);


--
-- Name: user_federation_mapper constraint_fedmapperpm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT constraint_fedmapperpm PRIMARY KEY (id);


--
-- Name: fed_user_consent_cl_scope constraint_fgrntcsnt_clsc_pm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.fed_user_consent_cl_scope
    ADD CONSTRAINT constraint_fgrntcsnt_clsc_pm PRIMARY KEY (user_consent_id, scope_id);


--
-- Name: user_consent_client_scope constraint_grntcsnt_clsc_pm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_consent_client_scope
    ADD CONSTRAINT constraint_grntcsnt_clsc_pm PRIMARY KEY (user_consent_id, scope_id);


--
-- Name: user_consent constraint_grntcsnt_pm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT constraint_grntcsnt_pm PRIMARY KEY (id);


--
-- Name: keycloak_group constraint_group; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_group
    ADD CONSTRAINT constraint_group PRIMARY KEY (id);


--
-- Name: group_attribute constraint_group_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.group_attribute
    ADD CONSTRAINT constraint_group_attribute_pk PRIMARY KEY (id);


--
-- Name: group_role_mapping constraint_group_role; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.group_role_mapping
    ADD CONSTRAINT constraint_group_role PRIMARY KEY (role_id, group_id);


--
-- Name: identity_provider_mapper constraint_idpm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider_mapper
    ADD CONSTRAINT constraint_idpm PRIMARY KEY (id);


--
-- Name: idp_mapper_config constraint_idpmconfig; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.idp_mapper_config
    ADD CONSTRAINT constraint_idpmconfig PRIMARY KEY (idp_mapper_id, name);


--
-- Name: migration_model constraint_migmod; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.migration_model
    ADD CONSTRAINT constraint_migmod PRIMARY KEY (id);


--
-- Name: offline_client_session constraint_offl_cl_ses_pk3; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.offline_client_session
    ADD CONSTRAINT constraint_offl_cl_ses_pk3 PRIMARY KEY (user_session_id, client_id, client_storage_provider, external_client_id, offline_flag);


--
-- Name: offline_user_session constraint_offl_us_ses_pk2; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.offline_user_session
    ADD CONSTRAINT constraint_offl_us_ses_pk2 PRIMARY KEY (user_session_id, offline_flag);


--
-- Name: protocol_mapper constraint_pcm; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT constraint_pcm PRIMARY KEY (id);


--
-- Name: protocol_mapper_config constraint_pmconfig; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.protocol_mapper_config
    ADD CONSTRAINT constraint_pmconfig PRIMARY KEY (protocol_mapper_id, name);


--
-- Name: realm_default_roles constraint_realm_default_roles; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_roles
    ADD CONSTRAINT constraint_realm_default_roles PRIMARY KEY (realm_id, role_id);


--
-- Name: redirect_uris constraint_redirect_uris; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.redirect_uris
    ADD CONSTRAINT constraint_redirect_uris PRIMARY KEY (client_id, value);


--
-- Name: required_action_config constraint_req_act_cfg_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.required_action_config
    ADD CONSTRAINT constraint_req_act_cfg_pk PRIMARY KEY (required_action_id, name);


--
-- Name: required_action_provider constraint_req_act_prv_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.required_action_provider
    ADD CONSTRAINT constraint_req_act_prv_pk PRIMARY KEY (id);


--
-- Name: user_required_action constraint_required_action; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_required_action
    ADD CONSTRAINT constraint_required_action PRIMARY KEY (required_action, user_id);


--
-- Name: resource_uris constraint_resour_uris_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_uris
    ADD CONSTRAINT constraint_resour_uris_pk PRIMARY KEY (resource_id, value);


--
-- Name: role_attribute constraint_role_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.role_attribute
    ADD CONSTRAINT constraint_role_attribute_pk PRIMARY KEY (id);


--
-- Name: user_attribute constraint_user_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_attribute
    ADD CONSTRAINT constraint_user_attribute_pk PRIMARY KEY (id);


--
-- Name: user_group_membership constraint_user_group; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_group_membership
    ADD CONSTRAINT constraint_user_group PRIMARY KEY (group_id, user_id);


--
-- Name: user_session_note constraint_usn_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_session_note
    ADD CONSTRAINT constraint_usn_pk PRIMARY KEY (user_session, name);


--
-- Name: web_origins constraint_web_origins; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.web_origins
    ADD CONSTRAINT constraint_web_origins PRIMARY KEY (client_id, value);


--
-- Name: client_scope_attributes pk_cl_tmpl_attr; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_attributes
    ADD CONSTRAINT pk_cl_tmpl_attr PRIMARY KEY (scope_id, name);


--
-- Name: client_scope pk_cli_template; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope
    ADD CONSTRAINT pk_cli_template PRIMARY KEY (id);


--
-- Name: databasechangeloglock pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: resource_server pk_resource_server; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server
    ADD CONSTRAINT pk_resource_server PRIMARY KEY (id);


--
-- Name: client_scope_role_mapping pk_template_scope; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_role_mapping
    ADD CONSTRAINT pk_template_scope PRIMARY KEY (scope_id, role_id);


--
-- Name: default_client_scope r_def_cli_scope_bind; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.default_client_scope
    ADD CONSTRAINT r_def_cli_scope_bind PRIMARY KEY (realm_id, scope_id);


--
-- Name: resource_attribute res_attr_pk; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_attribute
    ADD CONSTRAINT res_attr_pk PRIMARY KEY (id);


--
-- Name: keycloak_group sibling_names; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_group
    ADD CONSTRAINT sibling_names UNIQUE (realm_id, parent_group, name);


--
-- Name: identity_provider uk_2daelwnibji49avxsrtuf6xj33; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT uk_2daelwnibji49avxsrtuf6xj33 UNIQUE (provider_alias, realm_id);


--
-- Name: client_default_roles uk_8aelwnibji49avxsrtuf6xjow; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_default_roles
    ADD CONSTRAINT uk_8aelwnibji49avxsrtuf6xjow UNIQUE (role_id);


--
-- Name: client uk_b71cjlbenv945rb6gcon438at; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_b71cjlbenv945rb6gcon438at UNIQUE (realm_id, client_id);


--
-- Name: client_scope uk_cli_scope; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope
    ADD CONSTRAINT uk_cli_scope UNIQUE (realm_id, name);


--
-- Name: user_entity uk_dykn684sl8up1crfei6eckhd7; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_dykn684sl8up1crfei6eckhd7 UNIQUE (realm_id, email_constraint);


--
-- Name: resource_server_resource uk_frsr6t700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT uk_frsr6t700s9v50bu18ws5ha6 UNIQUE (name, owner, resource_server_id);


--
-- Name: resource_server_perm_ticket uk_frsr6t700s9v50bu18ws5pmt; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT uk_frsr6t700s9v50bu18ws5pmt UNIQUE (owner, requester, resource_server_id, resource_id, scope_id);


--
-- Name: resource_server_policy uk_frsrpt700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT uk_frsrpt700s9v50bu18ws5ha6 UNIQUE (name, resource_server_id);


--
-- Name: resource_server_scope uk_frsrst700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT uk_frsrst700s9v50bu18ws5ha6 UNIQUE (name, resource_server_id);


--
-- Name: realm_default_roles uk_h4wpd7w4hsoolni3h0sw7btje; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_roles
    ADD CONSTRAINT uk_h4wpd7w4hsoolni3h0sw7btje UNIQUE (role_id);


--
-- Name: user_consent uk_jkuwuvd56ontgsuhogm8uewrt; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT uk_jkuwuvd56ontgsuhogm8uewrt UNIQUE (client_id, client_storage_provider, external_client_id, user_id);


--
-- Name: realm uk_orvsdmla56612eaefiq6wl5oi; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm
    ADD CONSTRAINT uk_orvsdmla56612eaefiq6wl5oi UNIQUE (name);


--
-- Name: user_entity uk_ru8tt6t700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_ru8tt6t700s9v50bu18ws5ha6 UNIQUE (realm_id, username);


--
-- Name: idx_assoc_pol_assoc_pol_id; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_assoc_pol_assoc_pol_id ON public.associated_policy USING btree (associated_policy_id);


--
-- Name: idx_auth_config_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_auth_config_realm ON public.authenticator_config USING btree (realm_id);


--
-- Name: idx_auth_exec_flow; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_auth_exec_flow ON public.authentication_execution USING btree (flow_id);


--
-- Name: idx_auth_exec_realm_flow; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_auth_exec_realm_flow ON public.authentication_execution USING btree (realm_id, flow_id);


--
-- Name: idx_auth_flow_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_auth_flow_realm ON public.authentication_flow USING btree (realm_id);


--
-- Name: idx_cl_clscope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_cl_clscope ON public.client_scope_client USING btree (scope_id);


--
-- Name: idx_client_def_roles_client; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_client_def_roles_client ON public.client_default_roles USING btree (client_id);


--
-- Name: idx_client_id; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_client_id ON public.client USING btree (client_id);


--
-- Name: idx_client_init_acc_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_client_init_acc_realm ON public.client_initial_access USING btree (realm_id);


--
-- Name: idx_client_session_session; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_client_session_session ON public.client_session USING btree (session_id);


--
-- Name: idx_clscope_attrs; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_clscope_attrs ON public.client_scope_attributes USING btree (scope_id);


--
-- Name: idx_clscope_cl; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_clscope_cl ON public.client_scope_client USING btree (client_id);


--
-- Name: idx_clscope_protmap; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_clscope_protmap ON public.protocol_mapper USING btree (client_scope_id);


--
-- Name: idx_clscope_role; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_clscope_role ON public.client_scope_role_mapping USING btree (scope_id);


--
-- Name: idx_compo_config_compo; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_compo_config_compo ON public.component_config USING btree (component_id);


--
-- Name: idx_component_provider_type; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_component_provider_type ON public.component USING btree (provider_type);


--
-- Name: idx_component_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_component_realm ON public.component USING btree (realm_id);


--
-- Name: idx_composite; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_composite ON public.composite_role USING btree (composite);


--
-- Name: idx_composite_child; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_composite_child ON public.composite_role USING btree (child_role);


--
-- Name: idx_defcls_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_defcls_realm ON public.default_client_scope USING btree (realm_id);


--
-- Name: idx_defcls_scope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_defcls_scope ON public.default_client_scope USING btree (scope_id);


--
-- Name: idx_event_time; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_event_time ON public.event_entity USING btree (realm_id, event_time);


--
-- Name: idx_fedidentity_feduser; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fedidentity_feduser ON public.federated_identity USING btree (federated_user_id);


--
-- Name: idx_fedidentity_user; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fedidentity_user ON public.federated_identity USING btree (user_id);


--
-- Name: idx_fu_attribute; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_attribute ON public.fed_user_attribute USING btree (user_id, realm_id, name);


--
-- Name: idx_fu_cnsnt_ext; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_cnsnt_ext ON public.fed_user_consent USING btree (user_id, client_storage_provider, external_client_id);


--
-- Name: idx_fu_consent; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_consent ON public.fed_user_consent USING btree (user_id, client_id);


--
-- Name: idx_fu_consent_ru; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_consent_ru ON public.fed_user_consent USING btree (realm_id, user_id);


--
-- Name: idx_fu_credential; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_credential ON public.fed_user_credential USING btree (user_id, type);


--
-- Name: idx_fu_credential_ru; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_credential_ru ON public.fed_user_credential USING btree (realm_id, user_id);


--
-- Name: idx_fu_group_membership; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_group_membership ON public.fed_user_group_membership USING btree (user_id, group_id);


--
-- Name: idx_fu_group_membership_ru; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_group_membership_ru ON public.fed_user_group_membership USING btree (realm_id, user_id);


--
-- Name: idx_fu_required_action; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_required_action ON public.fed_user_required_action USING btree (user_id, required_action);


--
-- Name: idx_fu_required_action_ru; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_required_action_ru ON public.fed_user_required_action USING btree (realm_id, user_id);


--
-- Name: idx_fu_role_mapping; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_role_mapping ON public.fed_user_role_mapping USING btree (user_id, role_id);


--
-- Name: idx_fu_role_mapping_ru; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_fu_role_mapping_ru ON public.fed_user_role_mapping USING btree (realm_id, user_id);


--
-- Name: idx_group_attr_group; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_group_attr_group ON public.group_attribute USING btree (group_id);


--
-- Name: idx_group_role_mapp_group; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_group_role_mapp_group ON public.group_role_mapping USING btree (group_id);


--
-- Name: idx_id_prov_mapp_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_id_prov_mapp_realm ON public.identity_provider_mapper USING btree (realm_id);


--
-- Name: idx_ident_prov_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_ident_prov_realm ON public.identity_provider USING btree (realm_id);


--
-- Name: idx_keycloak_role_client; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_keycloak_role_client ON public.keycloak_role USING btree (client);


--
-- Name: idx_keycloak_role_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_keycloak_role_realm ON public.keycloak_role USING btree (realm);


--
-- Name: idx_offline_uss_createdon; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_offline_uss_createdon ON public.offline_user_session USING btree (created_on);


--
-- Name: idx_protocol_mapper_client; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_protocol_mapper_client ON public.protocol_mapper USING btree (client_id);


--
-- Name: idx_realm_attr_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_attr_realm ON public.realm_attribute USING btree (realm_id);


--
-- Name: idx_realm_clscope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_clscope ON public.client_scope USING btree (realm_id);


--
-- Name: idx_realm_def_grp_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_def_grp_realm ON public.realm_default_groups USING btree (realm_id);


--
-- Name: idx_realm_def_roles_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_def_roles_realm ON public.realm_default_roles USING btree (realm_id);


--
-- Name: idx_realm_evt_list_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_evt_list_realm ON public.realm_events_listeners USING btree (realm_id);


--
-- Name: idx_realm_evt_types_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_evt_types_realm ON public.realm_enabled_event_types USING btree (realm_id);


--
-- Name: idx_realm_master_adm_cli; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_master_adm_cli ON public.realm USING btree (master_admin_client);


--
-- Name: idx_realm_supp_local_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_realm_supp_local_realm ON public.realm_supported_locales USING btree (realm_id);


--
-- Name: idx_redir_uri_client; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_redir_uri_client ON public.redirect_uris USING btree (client_id);


--
-- Name: idx_req_act_prov_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_req_act_prov_realm ON public.required_action_provider USING btree (realm_id);


--
-- Name: idx_res_policy_policy; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_res_policy_policy ON public.resource_policy USING btree (policy_id);


--
-- Name: idx_res_scope_scope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_res_scope_scope ON public.resource_scope USING btree (scope_id);


--
-- Name: idx_res_serv_pol_res_serv; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_res_serv_pol_res_serv ON public.resource_server_policy USING btree (resource_server_id);


--
-- Name: idx_res_srv_res_res_srv; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_res_srv_res_res_srv ON public.resource_server_resource USING btree (resource_server_id);


--
-- Name: idx_res_srv_scope_res_srv; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_res_srv_scope_res_srv ON public.resource_server_scope USING btree (resource_server_id);


--
-- Name: idx_role_attribute; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_role_attribute ON public.role_attribute USING btree (role_id);


--
-- Name: idx_role_clscope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_role_clscope ON public.client_scope_role_mapping USING btree (role_id);


--
-- Name: idx_scope_mapping_role; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_scope_mapping_role ON public.scope_mapping USING btree (role_id);


--
-- Name: idx_scope_policy_policy; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_scope_policy_policy ON public.scope_policy USING btree (policy_id);


--
-- Name: idx_update_time; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_update_time ON public.migration_model USING btree (update_time);


--
-- Name: idx_us_sess_id_on_cl_sess; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_us_sess_id_on_cl_sess ON public.offline_client_session USING btree (user_session_id);


--
-- Name: idx_usconsent_clscope; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_usconsent_clscope ON public.user_consent_client_scope USING btree (user_consent_id);


--
-- Name: idx_user_attribute; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_attribute ON public.user_attribute USING btree (user_id);


--
-- Name: idx_user_consent; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_consent ON public.user_consent USING btree (user_id);


--
-- Name: idx_user_credential; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_credential ON public.credential USING btree (user_id);


--
-- Name: idx_user_email; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_email ON public.user_entity USING btree (email);


--
-- Name: idx_user_group_mapping; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_group_mapping ON public.user_group_membership USING btree (user_id);


--
-- Name: idx_user_reqactions; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_reqactions ON public.user_required_action USING btree (user_id);


--
-- Name: idx_user_role_mapping; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_user_role_mapping ON public.user_role_mapping USING btree (user_id);


--
-- Name: idx_usr_fed_map_fed_prv; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_usr_fed_map_fed_prv ON public.user_federation_mapper USING btree (federation_provider_id);


--
-- Name: idx_usr_fed_map_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_usr_fed_map_realm ON public.user_federation_mapper USING btree (realm_id);


--
-- Name: idx_usr_fed_prv_realm; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_usr_fed_prv_realm ON public.user_federation_provider USING btree (realm_id);


--
-- Name: idx_web_orig_client; Type: INDEX; Schema: public; Owner: keycloak
--

CREATE INDEX idx_web_orig_client ON public.web_origins USING btree (client_id);


--
-- Name: client_session_auth_status auth_status_constraint; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_auth_status
    ADD CONSTRAINT auth_status_constraint FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: identity_provider fk2b4ebc52ae5c3b34; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT fk2b4ebc52ae5c3b34 FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_attributes fk3c47c64beacca966; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_attributes
    ADD CONSTRAINT fk3c47c64beacca966 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: federated_identity fk404288b92ef007a6; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.federated_identity
    ADD CONSTRAINT fk404288b92ef007a6 FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: client_node_registrations fk4129723ba992f594; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_node_registrations
    ADD CONSTRAINT fk4129723ba992f594 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client_session_note fk5edfb00ff51c2736; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_note
    ADD CONSTRAINT fk5edfb00ff51c2736 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: user_session_note fk5edfb00ff51d3472; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_session_note
    ADD CONSTRAINT fk5edfb00ff51d3472 FOREIGN KEY (user_session) REFERENCES public.user_session(id);


--
-- Name: client_session_role fk_11b7sgqw18i532811v7o2dv76; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_role
    ADD CONSTRAINT fk_11b7sgqw18i532811v7o2dv76 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: redirect_uris fk_1burs8pb4ouj97h5wuppahv9f; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.redirect_uris
    ADD CONSTRAINT fk_1burs8pb4ouj97h5wuppahv9f FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: user_federation_provider fk_1fj32f6ptolw2qy60cd8n01e8; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_provider
    ADD CONSTRAINT fk_1fj32f6ptolw2qy60cd8n01e8 FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_session_prot_mapper fk_33a8sgqw18i532811v7o2dk89; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session_prot_mapper
    ADD CONSTRAINT fk_33a8sgqw18i532811v7o2dk89 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: realm_required_credential fk_5hg65lybevavkqfki3kponh9v; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_required_credential
    ADD CONSTRAINT fk_5hg65lybevavkqfki3kponh9v FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: resource_attribute fk_5hrm2vlf9ql5fu022kqepovbr; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_attribute
    ADD CONSTRAINT fk_5hrm2vlf9ql5fu022kqepovbr FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: user_attribute fk_5hrm2vlf9ql5fu043kqepovbr; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_attribute
    ADD CONSTRAINT fk_5hrm2vlf9ql5fu043kqepovbr FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: user_required_action fk_6qj3w1jw9cvafhe19bwsiuvmd; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_required_action
    ADD CONSTRAINT fk_6qj3w1jw9cvafhe19bwsiuvmd FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: keycloak_role fk_6vyqfe4cn4wlq8r6kt5vdsj5c; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT fk_6vyqfe4cn4wlq8r6kt5vdsj5c FOREIGN KEY (realm) REFERENCES public.realm(id);


--
-- Name: realm_smtp_config fk_70ej8xdxgxd0b9hh6180irr0o; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_smtp_config
    ADD CONSTRAINT fk_70ej8xdxgxd0b9hh6180irr0o FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_default_roles fk_8aelwnibji49avxsrtuf6xjow; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_default_roles
    ADD CONSTRAINT fk_8aelwnibji49avxsrtuf6xjow FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: realm_attribute fk_8shxd6l3e9atqukacxgpffptw; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_attribute
    ADD CONSTRAINT fk_8shxd6l3e9atqukacxgpffptw FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: composite_role fk_a63wvekftu8jo1pnj81e7mce2; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT fk_a63wvekftu8jo1pnj81e7mce2 FOREIGN KEY (composite) REFERENCES public.keycloak_role(id);


--
-- Name: authentication_execution fk_auth_exec_flow; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT fk_auth_exec_flow FOREIGN KEY (flow_id) REFERENCES public.authentication_flow(id);


--
-- Name: authentication_execution fk_auth_exec_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT fk_auth_exec_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: authentication_flow fk_auth_flow_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authentication_flow
    ADD CONSTRAINT fk_auth_flow_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: authenticator_config fk_auth_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.authenticator_config
    ADD CONSTRAINT fk_auth_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_session fk_b4ao2vcvat6ukau74wbwtfqo1; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_session
    ADD CONSTRAINT fk_b4ao2vcvat6ukau74wbwtfqo1 FOREIGN KEY (session_id) REFERENCES public.user_session(id);


--
-- Name: user_role_mapping fk_c4fqv34p1mbylloxang7b1q3l; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_role_mapping
    ADD CONSTRAINT fk_c4fqv34p1mbylloxang7b1q3l FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: client_scope_client fk_c_cli_scope_client; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_client
    ADD CONSTRAINT fk_c_cli_scope_client FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client_scope_client fk_c_cli_scope_scope; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_client
    ADD CONSTRAINT fk_c_cli_scope_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_scope_attributes fk_cl_scope_attr_scope; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_attributes
    ADD CONSTRAINT fk_cl_scope_attr_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_scope_role_mapping fk_cl_scope_rm_role; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_role_mapping
    ADD CONSTRAINT fk_cl_scope_rm_role FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: client_scope_role_mapping fk_cl_scope_rm_scope; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope_role_mapping
    ADD CONSTRAINT fk_cl_scope_rm_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_user_session_note fk_cl_usr_ses_note; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_user_session_note
    ADD CONSTRAINT fk_cl_usr_ses_note FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: protocol_mapper fk_cli_scope_mapper; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT fk_cli_scope_mapper FOREIGN KEY (client_scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_initial_access fk_client_init_acc_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_initial_access
    ADD CONSTRAINT fk_client_init_acc_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: component_config fk_component_config; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.component_config
    ADD CONSTRAINT fk_component_config FOREIGN KEY (component_id) REFERENCES public.component(id);


--
-- Name: component fk_component_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.component
    ADD CONSTRAINT fk_component_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_default_groups fk_def_groups_group; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT fk_def_groups_group FOREIGN KEY (group_id) REFERENCES public.keycloak_group(id);


--
-- Name: realm_default_groups fk_def_groups_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT fk_def_groups_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_default_roles fk_evudb1ppw84oxfax2drs03icc; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_roles
    ADD CONSTRAINT fk_evudb1ppw84oxfax2drs03icc FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: user_federation_mapper_config fk_fedmapper_cfg; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_mapper_config
    ADD CONSTRAINT fk_fedmapper_cfg FOREIGN KEY (user_federation_mapper_id) REFERENCES public.user_federation_mapper(id);


--
-- Name: user_federation_mapper fk_fedmapperpm_fedprv; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT fk_fedmapperpm_fedprv FOREIGN KEY (federation_provider_id) REFERENCES public.user_federation_provider(id);


--
-- Name: user_federation_mapper fk_fedmapperpm_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT fk_fedmapperpm_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: associated_policy fk_frsr5s213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT fk_frsr5s213xcx4wnkog82ssrfy FOREIGN KEY (associated_policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: scope_policy fk_frsrasp13xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT fk_frsrasp13xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog82sspmt; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog82sspmt FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_server_resource fk_frsrho213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT fk_frsrho213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog83sspmt; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog83sspmt FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog84sspmt; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog84sspmt FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: associated_policy fk_frsrpas14xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT fk_frsrpas14xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: scope_policy fk_frsrpass3xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT fk_frsrpass3xcx4wnkog82ssrfy FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: resource_server_perm_ticket fk_frsrpo2128cx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrpo2128cx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_server_policy fk_frsrpo213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT fk_frsrpo213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_scope fk_frsrpos13xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT fk_frsrpos13xcx4wnkog82ssrfy FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_policy fk_frsrpos53xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT fk_frsrpos53xcx4wnkog82ssrfy FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_policy fk_frsrpp213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT fk_frsrpp213xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_scope fk_frsrps213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT fk_frsrps213xcx4wnkog82ssrfy FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: resource_server_scope fk_frsrso213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT fk_frsrso213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: composite_role fk_gr7thllb9lu8q4vqa4524jjy8; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT fk_gr7thllb9lu8q4vqa4524jjy8 FOREIGN KEY (child_role) REFERENCES public.keycloak_role(id);


--
-- Name: user_consent_client_scope fk_grntcsnt_clsc_usc; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_consent_client_scope
    ADD CONSTRAINT fk_grntcsnt_clsc_usc FOREIGN KEY (user_consent_id) REFERENCES public.user_consent(id);


--
-- Name: user_consent fk_grntcsnt_user; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT fk_grntcsnt_user FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: group_attribute fk_group_attribute_group; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.group_attribute
    ADD CONSTRAINT fk_group_attribute_group FOREIGN KEY (group_id) REFERENCES public.keycloak_group(id);


--
-- Name: keycloak_group fk_group_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_group
    ADD CONSTRAINT fk_group_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: group_role_mapping fk_group_role_group; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.group_role_mapping
    ADD CONSTRAINT fk_group_role_group FOREIGN KEY (group_id) REFERENCES public.keycloak_group(id);


--
-- Name: group_role_mapping fk_group_role_role; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.group_role_mapping
    ADD CONSTRAINT fk_group_role_role FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: realm_default_roles fk_h4wpd7w4hsoolni3h0sw7btje; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_default_roles
    ADD CONSTRAINT fk_h4wpd7w4hsoolni3h0sw7btje FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: realm_enabled_event_types fk_h846o4h0w8epx5nwedrf5y69j; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_enabled_event_types
    ADD CONSTRAINT fk_h846o4h0w8epx5nwedrf5y69j FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_events_listeners fk_h846o4h0w8epx5nxev9f5y69j; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_events_listeners
    ADD CONSTRAINT fk_h846o4h0w8epx5nxev9f5y69j FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: identity_provider_mapper fk_idpm_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider_mapper
    ADD CONSTRAINT fk_idpm_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: idp_mapper_config fk_idpmconfig; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.idp_mapper_config
    ADD CONSTRAINT fk_idpmconfig FOREIGN KEY (idp_mapper_id) REFERENCES public.identity_provider_mapper(id);


--
-- Name: keycloak_role fk_kjho5le2c0ral09fl8cm9wfw9; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT fk_kjho5le2c0ral09fl8cm9wfw9 FOREIGN KEY (client) REFERENCES public.client(id);


--
-- Name: web_origins fk_lojpho213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.web_origins
    ADD CONSTRAINT fk_lojpho213xcx4wnkog82ssrfy FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client_default_roles fk_nuilts7klwqw2h8m2b5joytky; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_default_roles
    ADD CONSTRAINT fk_nuilts7klwqw2h8m2b5joytky FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: scope_mapping fk_ouse064plmlr732lxjcn1q5f1; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_mapping
    ADD CONSTRAINT fk_ouse064plmlr732lxjcn1q5f1 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: scope_mapping fk_p3rh9grku11kqfrs4fltt7rnq; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.scope_mapping
    ADD CONSTRAINT fk_p3rh9grku11kqfrs4fltt7rnq FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: client fk_p56ctinxxb9gsk57fo49f9tac; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fk_p56ctinxxb9gsk57fo49f9tac FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: protocol_mapper fk_pcm_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT fk_pcm_realm FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: credential fk_pfyr0glasqyl0dei3kl69r6v0; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.credential
    ADD CONSTRAINT fk_pfyr0glasqyl0dei3kl69r6v0 FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: protocol_mapper_config fk_pmconfig; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.protocol_mapper_config
    ADD CONSTRAINT fk_pmconfig FOREIGN KEY (protocol_mapper_id) REFERENCES public.protocol_mapper(id);


--
-- Name: default_client_scope fk_r_def_cli_scope_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.default_client_scope
    ADD CONSTRAINT fk_r_def_cli_scope_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: default_client_scope fk_r_def_cli_scope_scope; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.default_client_scope
    ADD CONSTRAINT fk_r_def_cli_scope_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_scope fk_realm_cli_scope; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.client_scope
    ADD CONSTRAINT fk_realm_cli_scope FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: required_action_provider fk_req_act_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.required_action_provider
    ADD CONSTRAINT fk_req_act_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: resource_uris fk_resource_server_uris; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.resource_uris
    ADD CONSTRAINT fk_resource_server_uris FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: role_attribute fk_role_attribute_id; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.role_attribute
    ADD CONSTRAINT fk_role_attribute_id FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: realm_supported_locales fk_supported_locales_realm; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm_supported_locales
    ADD CONSTRAINT fk_supported_locales_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: user_federation_config fk_t13hpu1j94r2ebpekr39x5eu5; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_federation_config
    ADD CONSTRAINT fk_t13hpu1j94r2ebpekr39x5eu5 FOREIGN KEY (user_federation_provider_id) REFERENCES public.user_federation_provider(id);


--
-- Name: realm fk_traf444kk6qrkms7n56aiwq5y; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.realm
    ADD CONSTRAINT fk_traf444kk6qrkms7n56aiwq5y FOREIGN KEY (master_admin_client) REFERENCES public.client(id);


--
-- Name: user_group_membership fk_user_group_user; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.user_group_membership
    ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: policy_config fkdc34197cf864c4e43; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.policy_config
    ADD CONSTRAINT fkdc34197cf864c4e43 FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: identity_provider_config fkdc4897cf864c4e43; Type: FK CONSTRAINT; Schema: public; Owner: keycloak
--

ALTER TABLE ONLY public.identity_provider_config
    ADD CONSTRAINT fkdc4897cf864c4e43 FOREIGN KEY (identity_provider_id) REFERENCES public.identity_provider(internal_id);


--
-- PostgreSQL database dump complete
--

